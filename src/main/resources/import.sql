INSERT INTO usuario (id,loginname,nombre,password,rol) VALUES(1,'admin','admin','$2a$10$oyQsB0p2342oiBU9KAlAfOW/1vuZ2zjL4I8//gsC/eq1qhHBGtfWW','admin')
INSERT INTO premio_juego (id,nombre_premio) VALUES (1,'Juego del Año'),(2,'Mejor Narrativa'),(3,'Mejor Banda Sonora'),(4,'Mejor Juego de Accion'),(5,'Mejor juego de Aventura'),(6,'Mejor juego de rol'),(7,'Mejor Juego de Lucha'),(8,'Mejor Juego Familiar'),(9,'Mejor Juego de Estrategia'),(10,'Mejor Juego de Deportes/Carreras'),(11,'Mejor Juego Multijugador'),(12,'Mejor Juego como Servicio'),(13,'Mejor juego de eSports'),(14,'Mejor Evento de eSports'),(15,'Mejor Soporte a la Comunidad'),(16,'Juego de Mayor Impacto'),(17,'Mejor Juego Indie'),(18,'Mejor Juego para Moviles'),(19,'Mejor Juego VR');

INSERT INTO categoria_participante (id,nombre) VALUES (1,'Director'),(2,'Desarrollador'),(3,'Audio'),(4,'eSports'),(5,'Guionista'),(6,'Actores'),(7,'Creador de Contenido'); 

INSERT INTO categoria_juego (id,nombre) values(1,'Acción'),(2,'Arcade'),(3,'Aventura'),(4,'Beat em all'),(5,'MMO'),(6,'Cooperación'),(7,'Lucha'),(8,'Deportes'),(9,'Familiar'),(10,'Entretenimiento'),(11,'Estrategia'),(12,'FPS'),(13,'Gestión'),(14,'Carreras'),(15,'Plataformas'),(16,'Multijugador'),(17,'RPG'),(18,'Indie'),(19,'Shoot em up'),(20,'Móviles'),(21,'Simulación'),(22,'VR'),(23,'Wargame'),(24,'JRPG');

INSERT INTO plataforma (id,nombre,familia) VALUES (1,'Playstation 1','Sony'),(2,'Playstation 2','Sony'),(3,'Playstation 3','Sony'),(4,'Playstation 4','Sony'),(5,'Playstation Portable','Sony'),(6,'Playstation Vita','Sony'),(7,'Xbox','Microsoft'),(8,'Xbox 2','Microsoft'),(9,'Xbox 360','Microsoft'),(10,'Xbox One','Microsoft'),(11,'Nintendo Entertainement','Nintendo'),(12,'Super Nintendo Entertainement','Nintendo'),(13,'Nintendo 64','Nintendo'),(14,'Nintendo Gameboy','Nintendo'),(15,'Nintendo Gameboy Advanced','Nintendo'),(16,'Nintendo DS','Nintendo'),(17,'Nintendo 3DS','Nintendo'),(18,'Nintendo Switch','Nintendo'),(19,'PC','PC'),(20,'Sega Saturn','Sega'),(21,'Sega Dreamcast','Sega'),(22,'Sega Megadrive','Sega'),(23,'Sega Master System','Sega'),(24,'NEO-GEO','SNK'),(25,'Atari 2600','Atari'),(26,'Atari XEGS','Atari'),(27,'Atari Pong','Atari');

INSERT INTO premio_participante (id, nombre_premio)  values (1,'Mejor dirección'),(2,'Mejor dirección Artística'),(3,'Mejor producción audiovisual'),(4,'Mejor anfitrión eSports'),(5,'Mejor creador de contenido'),(6,'Mejor entrenador de eSports'),(7,'Mejor jugador de eSports'),(8,'Mejor equipo de eSports'),(9,'Mejor guionista'),(10,'Mejor actor de doblaje'),(11,'Mejor desarrolladora'),(12,'Mejor actor');