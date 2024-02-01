
CREATE DATABASE
	V360ToDoList;
    
USE
	V360ToDoList;
    
CREATE TABLE
	listas (
		lista_id INT AUTO_INCREMENT PRIMARY KEY,
        lista_nome VARCHAR(255) NOT NULL
    );

CREATE TABLE
	items (
		item_id INT AUTO_INCREMENT PRIMARY KEY,
        item_desc VARCHAR(255) NOT NULL,
        lista_id INT,
        item_status BOOLEAN DEFAULT FALSE,
        FOREIGN KEY (lista_id) REFERENCES listas(lista_id) ON DELETE CASCADE
    );
