package br.com.carolinefeitosa.desafiotodolist;

import static br.com.carolinefeitosa.desafiotodolist.TestConstants.TODO;
import static br.com.carolinefeitosa.desafiotodolist.TestConstants.TODOS;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.carolinefeitosa.desafiotodolist.entity.ToDo;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Sql("/remove.sql")
class ToDoApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateToDoSuccess() {
		var todo = new ToDo("todo 1", "desc todo 1", false, 1);

		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isCreated()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].status").isEqualTo(todo.isStatus())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	public void testCreateToDoFailure() {
		webTestClient
				.post()
				.uri("/todos")
				.bodyValue(
						new ToDo(" ", " ", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testUpdateToDoSuccess() {
		var todo = new ToDo(TODO.getId(), TODO.getName() + " Up", TODO.getName() + " Up", !TODO.isStatus(),
				TODO.getPriority() + 1);

		webTestClient
				.put()
				.uri("/todos/" + TODO.getId())
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0].name").isEqualTo(todo.getName())
				.jsonPath("$[0].description").isEqualTo(todo.getDescription())
				.jsonPath("$[0].status").isEqualTo(todo.isStatus())
				.jsonPath("$[0].priority").isEqualTo(todo.getPriority());
	}

	@Test
	public void testUpdateToDoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.put()
				.uri("/todos/" + unexinstingId)
				.bodyValue(
						new ToDo(unexinstingId, "", "", false, 0))
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testDeleteToDoSuccess() {
		webTestClient
				.delete()
				.uri("/todos/" + TODOS.get(0).getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(4)
				.jsonPath("$[0].name").isEqualTo(TODOS.get(1).getName())
				.jsonPath("$[0].description").isEqualTo(TODOS.get(1).getDescription())
				.jsonPath("$[0].status").isEqualTo(TODOS.get(1).isStatus())
				.jsonPath("$[0].priority").isEqualTo(TODOS.get(1).getPriority());
	}

	@Test
	public void testDeleteToDoFailure() {
		var unexinstingId = 1L;

		webTestClient
				.delete()
				.uri("/todos/" + unexinstingId)
				.exchange()
				.expectStatus().isBadRequest();
	}

	@Sql("/import.sql")
	@Test
	public void testListTodos() throws Exception {
		webTestClient
				.get()
				.uri("/todos")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(5)
				.jsonPath("$[0]").isEqualTo(TODOS.get(0))
				.jsonPath("$[1]").isEqualTo(TODOS.get(1))
				.jsonPath("$[2]").isEqualTo(TODOS.get(2))
				.jsonPath("$[3]").isEqualTo(TODOS.get(3))
				.jsonPath("$[4]").isEqualTo(TODOS.get(4));
	}
}

