package br.com.carolinefeitosa.desafiotodolist;

import java.util.ArrayList;
import java.util.List;

import br.com.carolinefeitosa.desafiotodolist.entity.ToDo;

public class TestConstants {
    public static final List<ToDo> TODOS = new ArrayList<>() {
      {
        add(new ToDo(9995L, "@carolinefeitosa", "Curtir", false, 1));
        add(new ToDo(9996L, "@carolinefeitosa", "Comentar", false, 1));
        add(new ToDo(9997L, "@carolinefeitosa", "Compartilhar", false, 1));
        add(new ToDo(9998L, "@carolinefeitosa", "Se Inscrever", false, 1));
        add(new ToDo(9999L, "@carolinefeitosa", "Ativar as Notificações", false, 1));
      }
    };
  
    public static final ToDo TODO = TODOS.get(0);
  }