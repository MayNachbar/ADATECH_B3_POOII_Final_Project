# ADATECH_B3_POOII_Final_Project
Projeto final do módulo II do curso da ADA Tech em parceria com a B3 - Programação Orientada a Objetos II

# AdaTask: Aplicativo de Gerenciamento de Tarefas via Console

Um aplicativo que permite criar, ler, editar e deletar tarefas pessoais, de estudo e de trabalho. O ADATASK oferece funcionalidades completas de gerenciamento de tarefas, proporcionando uma experiência simples e eficiente.

## Como executar:

Para executar a aplicação, basta rodar o arquivo `AdaTaskApplication.java` e seguir as instruções no console.

## Levantamento e Análise de Requisitos do Sistema:

### Requisitos Funcionais:

1. O sistema ADATASK permite que o usuário crie tarefas pessoais, de estudo e de trabalho.
2. As tarefas possuem diferentes atributos, dependendo do tipo:
   - **Tarefas pessoais:** título, descrição, responsável, prioridade, status e prazo.
   - **Tarefas de estudo:** título, descrição, responsável, matéria, prioridade, status e prazo.
   - **Tarefas de trabalho:** título, descrição, responsável, tipo de trabalho, prioridade, status e prazo.
3. O usuário pode criar, editar e deletar tarefas existentes.
4. O sistema permite a visualização de todas as tarefas ou filtragem por critérios específicos (ID, Deadline, Categoria de tarefa).
5. As tarefas são armazenadas em um banco de dados em memória, em 3 listas diferentes, uma para cada categoria de tarefa.

### Requisitos Não Funcionais:

1. O sistema ADATASK foi implementado em Java.
2. Fácil de usar, com mensagens claras para orientar o usuário.
3. O design do sistema segue os princípios SOLID para facilidade de manutenção e extensibilidade.

## Definição dos Objetos do Sistema:

1. **Tarefa Base (BaseTask):** Representa uma tarefa genérica com atributos como title, description, responsible, priority, status e deadline.
2. **Tarefa Pessoal (PersonalTask):** Subclasse de Tarefa, não contém atributos específicos para tarefas pessoais.
3. **Tarefa de Estudo (StudyTask):** Subclasse de Tarefa, contém atributos específicos para tarefas de estudo: subject.
4. **Tarefa de Trabalho (WorkTask):** Subclasse de Tarefa, contém atributos específicos para tarefas profissionais: workType.

## Definição dos Atores do Sistema:

1. **Usuário:** Interage com o sistema através do console. Pode criar, visualizar, editar e deletar tarefas.

## Definição e Detalhamento dos Casos de Uso:

### 1. Criar Tarefa:

- Ator: Usuário
- Fluxo Principal:
1.  O usuário escolhe o tipo de tarefa (personal, study ou work).
2.	O sistema solicita informações como title, description, priority e deadline se for uma personalTask, e se for studyTask solicita também a subject, e se for workTask solicita workType. Os atributos responsible e  status são pré-definidos.
3.	O responsible recebe o nome do usário inserido anteriormente e o status é definido como padrão: TODO.
4.	O usuário fornece as informações.
5.	O sistema cria e adiciona a tarefa na lista da categoria correspondente.

### 2. Visualizar Tarefa:

- Ator: Usuário
- Fluxo Principal:
1.  O usuário escolhe visualizar todas as tarefas ou aplicar filtros.
2.	O sistema exibe a lista de tarefas correspondente.

### 3. Editar Tarefa:

- Ator: Usuário
- Fluxo Principal:
1.  O usuário escolhe a opção de editar tarefa.
2.	O usuário escolhe o ID da tarefa a ser editada.
3.	O usuário escolhe qual campo quer editar.
4.	O usuário insere um novo valor para o campo escolhido.
5.	O usuário atualiza as informações desejadas.
6.	O sistema salva as alterações.

### 4. Deletar Tarefa:

- Ator: Usuário
- Fluxo Principal:
1.  O usuário escolhe a opção de deletar tarefa.
2.	O usuário escolhe o ID da tarefa a ser deletada.
3.	O usuário confirma se quer deletar a tarefa.
4.	O sistema confirma a exclusão.

## Definição das Classes:

1. **BaseTask (Classe Abstrata):**
   - Atributos: id, title, description, responsible, priority, status, deadline, createdAt, updatedAt e deletedAt.
   - Métodos: getters e setters para os atributos.

2. **PersonalTask (Subclasse de BaseTask):**
   - Sem atributos adicionais.

3. **StudyTask (Subclasse de BaseTask):**
   - Atributos adicionais: subject.

4. **WorkTask (Subclasse de BaseTask):**
   - Atributos adicionais: workType.

5. **User:**
   - Atributos: Id e name.

6. **Status:**
   - Enum: TODO, INPROGRESS, DONE, CANCELLED.

7. **Priority:**
   - Enum: LOW, MEDIUM, HIGH.

8. **TaskRepository (Interface):**
   - Métodos: saveTask, readAllTasks, readTasksById, readTasksByDeadline, updateTask, deleteTask.

9. **InMemoryTaskRepository (Implementação de TaskRepository):**
   - Implementação dos métodos da interface.

10. **Task (Interface):**
    - Métodos: postTask, getAllTasks, getTasksById, getTasksByDeadline, putTask, deleteTask.

11. **TaskService (Implementação de Task):**
    - Atributo: TaskRepository.
    - Implementação dos métodos da interface.

12. **PersonalTaskController:**
    - Atributo: TaskService.
    - Métodos: start, showMenu, createPersonalTask, findAllPersonalTasks, printTasks, findPersonalTaskById, findPersonalTaskByDeadline, updatePersonalTask, deletePersonalTask.

13. **StudyTaskController:**
    - Atributo: TaskService.
    - Métodos: start, showMenu, createStudyTask, findAllStudyTasks, printTasks, findStudyTaskById, findStudyTaskByDeadline, updateStudyTask, deleteStudyTask.

14. **WorkTaskController:**
    - Atributos: TaskService.
    - Métodos: start, showMenu, createWorkTask, findAllWorkTasks, printTasks, findWorkTaskById, findWorkTaskByDeadline, updateWorkTask, deleteWorkTask.
