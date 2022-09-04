package fr.stephanecastrec.designpatterns

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CreationControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    @Test
    fun `Assert factory for unknown type`() {
        val params = HashMap<String, String>()
        params.put("type", "DUMMY")
        val entity = restTemplate.getForEntity<String>("/creation/factory", params)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.BAD_REQUEST)
    }

    @Test
    fun `Assert factory for known type CARS`() {
        val params = HashMap<String, String>()
        params.put("type", "Cars")
        val entity = restTemplate.getForEntity<String>("/creation/factory?type=Cars")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("car")
    }
}