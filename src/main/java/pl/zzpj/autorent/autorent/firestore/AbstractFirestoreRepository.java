package pl.zzpj.autorent.autorent.firestore;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.database.annotations.Nullable;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Slf4j
public abstract class AbstractFirestoreRepository<T> {
    private final CollectionReference collectionReference;
    private final String collectionName;
    private final Class<T> parameterizedType;

    protected AbstractFirestoreRepository(Firestore firestore, String collection) {
        this.collectionReference = firestore.collection(collection);
        this.collectionName = collection;
        this.parameterizedType = getParameterizedType();
    }

    private Class<T> getParameterizedType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }

    public boolean save(T model) {
        String documentId = getDocumentId(model);
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).set(model);
        try {
            log.info("{}-{} saved at{}", collectionName, documentId, resultApiFuture.get().getUpdateTime());
            return true;
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error saving {}={} {}", collectionName, documentId, e.getMessage());
        }

        return false;
    }

    public void delete(T model) {
        String documentId = getDocumentId(model);
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).delete();

    }

    public void deleteById(String id) {
        collectionReference.document(id).delete();
    }

    public List<T> retrieveAll() {
        ApiFuture<QuerySnapshot> querySnapshotApiFuture = collectionReference.get();

        try {
            List<QueryDocumentSnapshot> queryDocumentSnapshots = querySnapshotApiFuture.get().getDocuments();
            System.out.println(queryDocumentSnapshots.get(0).toString());
            return queryDocumentSnapshots.stream()
                    .map(queryDocumentSnapshot -> queryDocumentSnapshot.toObject(parameterizedType))
                    .collect(Collectors.toList());

        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred while retrieving all document for {}", collectionName);
        }
        return Collections.<T>emptyList();

    }

    @Nullable
    public ArrayList<Optional<T>> findBy(String fieldName, String fieldValue) {
        Query query = collectionReference.whereEqualTo(fieldName, fieldValue);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        ArrayList<Optional<T>> result = new ArrayList<>();

        try {
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                result.add(Optional.ofNullable(document.toObject(parameterizedType)));
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        if(result.size() > 0) {
            return result;
        }
        else {
            return null;
        }
    }

    public Optional<T> get(String documentId) {
        DocumentReference documentReference = collectionReference.document(documentId);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = documentReference.get();

        try {
            T t = null;
            DocumentSnapshot documentSnapshot = documentSnapshotApiFuture.get();

            if (documentSnapshot.exists()) {
                t = documentSnapshot.toObject(parameterizedType);
            }
            return Optional.of(t);

        } catch (InterruptedException | ExecutionException e) {
            log.error("Exception occurred retrieving: {} {}, {}", collectionName, documentId, e.getMessage());
        }

        return Optional.empty();

    }

    public void update(String documentId, T model){
        ApiFuture<WriteResult> resultApiFuture = collectionReference.document(documentId).set(model);

    }

    protected String getDocumentId(T t) {
        Object key;
        Class<?> clzz = t.getClass();
        do {
            key = getKeyFromFields(clzz, t);
            clzz = clzz.getSuperclass();
        } while (key == null && clzz != null);

        if (key == null) {
            return UUID.randomUUID().toString();
        }
        return String.valueOf(key);
    }

    private Object getKeyFromFields(Class<?> clazz, Object t) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(DocumentId.class))
                .findFirst()
                .map(field -> getValue(t, field))
                .orElse(null);
    }

    @Nullable
    private Object getValue(Object t, java.lang.reflect.Field field) {
        field.setAccessible(true);
        try {
            return field.get(t);
        } catch (IllegalAccessException e) {
            log.error("Error in getting documentId key", e);
        }
        return null;
    }

    protected CollectionReference getCollectionReference() {
        return this.collectionReference;
    }

    protected Class<T> getType() {
        return this.parameterizedType;
    }

    public String generateNewDocumentId() {
        DocumentReference ref = collectionReference.document();
        return ref.getId();
    }
}
