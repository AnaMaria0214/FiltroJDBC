USE jdbc;

CREATE TABLE clientes (
id_Cliente INT(11) PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(255) NOT NULL,
apellido VARCHAR(255) NOT NULL,
email VARCHAR(255) NOT NULL 
);

CREATE TABLE tiendas (
id_Tienda INT(11) PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(255) NOT NULL,
ubicacion VARCHAR(255) NOT NULL
);

CREATE TABLE productos (
id_Producto INT(11)PRIMARY KEY AUTO_INCREMENT,
nombre VARCHAR(255) NOT NULL,
precio DOUBLE (10,2) NOT NULL,
id_Tienda INT(11),
    CONSTRAINT fk_Id_Tienda FOREIGN KEY (Id_Tienda)
        REFERENCES tiendas (id_Tienda)
        ON DELETE CASCADE
);

CREATE TABLE compras (
id_Compra INT(11)PRIMARY KEY AUTO_INCREMENT,
id_Cliente INT(11),
    CONSTRAINT fk_Id_Cliente FOREIGN KEY (Id_Cliente)
        REFERENCES clientes (id_Cliente)
        ON DELETE CASCADE,
id_Producto INT(11),
    CONSTRAINT fk_Id_Producto FOREIGN KEY (Id_Producto)
        REFERENCES productos (id_Producto)
        ON DELETE CASCADE,
fecha_Compra DATE NOT NULL,
cantidad INT(11) NOT NULL
);
