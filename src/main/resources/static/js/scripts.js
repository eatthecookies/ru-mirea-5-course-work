document.addEventListener("DOMContentLoaded", function() {
    const loginForm = document.getElementById("loginForm");
    const registerForm = document.getElementById("registerForm");
    const taskForm = document.getElementById("taskForm");

    // Обработка формы входа
    if (loginForm) {
        loginForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            fetch('/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            })
                .then(response => {
                    if (response.ok) {
                        window.location.href = "tasks.html"; // Перенаправление на страницу задач
                    } else {
                        document.getElementById("loginMessage").innerText = "Неверное имя пользователя или пароль.";
                    }
                });
        });
    }

    // Обработка формы регистрации
    if (registerForm) {
        registerForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            fetch('/auth/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ username, password })
            })
                .then(response => {
                    if (response.ok) {
                        document.getElementById("registerMessage").innerText = "Пользователь зарегистрирован успешно.";
                    } else {
                        document.getElementById("registerMessage").innerText = "Имя пользователя уже существует.";
                    }
                });
        });
    }

    // Обработка формы задач
    if (taskForm) {
        taskForm.addEventListener("submit", function(event) {
            event.preventDefault();
            const taskTitle = document.getElementById("taskTitle").value;
            const taskDescription = document.getElementById("taskDescription").value;

            fetch('/tasks/create', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ title: taskTitle, description: taskDescription })
            })
                .then(response => {
                    if (response.ok) {
                        loadTasks(); // Обновить список задач
                    }
                });
        });

        // Функция для загрузки задач
        function loadTasks() {
            fetch('/tasks')
                .then(response => response.json())
                .then(tasks => {
                    const taskList = document.getElementById("taskList");
                    taskList.innerHTML = "";
                    tasks.forEach(task => {
                        const taskItem = document.createElement("div");
                        taskItem.innerText = task.title;
                        taskList.appendChild(taskItem);
                    });
                });
        }

        loadTasks(); // Загрузить задачи при загрузке страницы
    }
});
