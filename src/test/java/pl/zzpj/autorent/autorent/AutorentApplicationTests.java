package pl.zzpj.autorent.autorent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.zzpj.autorent.autorent.controllers.CarController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AutorentApplicationTests {


	@Autowired
	private CarController carController;

	@Test
	void contextLoads() {
		assertThat(carController).isNotNull();
	}

}
