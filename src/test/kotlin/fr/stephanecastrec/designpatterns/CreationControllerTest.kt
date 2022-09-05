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
class CreationControllerTests(@Autowired val restTemplate: TestRestTemplate) {

    @LocalServerPort
    var randomServerPort = 0
    @Test
    fun `Assert builder`() {
        val entity = restTemplate.getForEntity<String>("/creation/builder?seats=5&color=red")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        System.out.println(entity.body)
        assertThat(entity.body).contains("red")
        assertThat(entity.body).contains("seats\":5")
    }

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

    @Test
    fun `Assert factory for known type Train`() {
        val entity = restTemplate.getForEntity<String>("/creation/factory?type=Train")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("train")
    }

    @Test
    fun `Assert singleton is working`() {
        val baseUrl = "http://localhost:$randomServerPort/creation/singleton/"
        val uri = URI(baseUrl)
        val headers = HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
        val map: MultiValueMap<String, String> = LinkedMultiValueMap()
        map.add("key", "type")
        map.add("value", "car")
        val request: HttpEntity<MultiValueMap<String, String>?> = HttpEntity<MultiValueMap<String, String>?>(map, headers)

        val response: ResponseEntity<String> = restTemplate.postForEntity(
            uri, request, String::class.java)
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK)

        var entity = restTemplate.getForEntity<String>("/creation/singleton?key=type")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).contains("car")

        entity = restTemplate.getForEntity<String>("/creation/singleton?key=toto")
        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(entity.body).isNull()
    }
}