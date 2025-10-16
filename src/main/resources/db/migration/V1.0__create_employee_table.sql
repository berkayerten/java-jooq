CREATE TABLE employee (
      id SERIAL PRIMARY KEY,
      name VARCHAR(100) NOT NULL,
      email VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE posts (
      id SERIAL PRIMARY KEY,
      user_id INT NOT NULL REFERENCES employee(id) ON DELETE CASCADE,
      title VARCHAR(200) NOT NULL,
      content TEXT,
      created_at TIMESTAMP DEFAULT now()
);