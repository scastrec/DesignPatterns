package fr.stephanecastrec.designpatterns

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.*
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.net.URI


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class StructureControllerTest(@Autowired val restTemplate: TestRestTemplate) {

    @LocalServerPort
    var randomServerPort = 0
    @Test
    fun `Assert adaptor - round type ok`() {
        val entity = restTemplate.getForEntity<String>("/structure/adaptor?holeRadius=5&roundRadius=1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        System.out.println(entity.body)
        assertThat(entity.body).contains("true")
    }

    @Test
    fun `Assert adaptor - round type Nok`() {
        val entity = restTemplate.getForEntity<String>("/structure/adaptor?holeRadius=1&roundRadius=8")
        System.out.println(entity.body)
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        System.out.println(entity.body)
        assertThat(entity.body).contains("false")
    }

    @Test
    fun `Assert adaptor - square type ok`() {
        val entity = restTemplate.getForEntity<String>("/structure/adaptor?holeRadius=5&squareWidth=1")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        System.out.println(entity.body)
        assertThat(entity.body).contains("true")
    }

    @Test
    fun `Assert adaptor - square type Nok`() {
        val entity = restTemplate.getForEntity<String>("/structure/adaptor?holeRadius=1&squareWidth=8")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        System.out.println(entity.body)
        assertThat(entity.body).contains("false")
    }
}