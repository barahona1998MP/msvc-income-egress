const { db } = require("../config/cnn");
const bcrypt = require('bcryptjs');
const axios = require("axios");

/* List Car */
const getCars = async (req, res) => {
    try {
        const userId = req.params.userId;
        console.log(userId)
        const response = await axios.get(`http://localhost:8081/car/${userId}/user`)
        console.log(response)
        const cars = response.data;  
        res.json((cars))
    } catch (error) {
        console.error('Error retrieving cars for user', error);
        res.status(500).send('Error retrieving cars for user');
    }
}


/*Crear usuario*/
const createUser = async (req, res) => {

    try {
        const {nombre, apellido, usuario, contrasena, correo} = req.body


            // Verificar si el correo ya está registrado
        const correoExistente = await db.oneOrNone('SELECT * FROM usuarios WHERE correo = $1', [correo]);
        if (correoExistente) {
            return res.status(400).json({ message: 'El correo ya está registrado' });
        }

        // Verificar si el usuario ya está registrado
        const usuarioExistente = await db.oneOrNone('SELECT * FROM usuarios WHERE usuario = $1', [usuario]);
        if (usuarioExistente) {
            return res.status(400).json({ message: 'El usuario ya está registrado' });
        }

        // Encriptar la contraseña antes de guardarla en la base de datos
        const hashedPassword = await bcrypt.hash(contrasena, 10);


        // Insertar el nuevo usuario en la base de datos
        const response = await db.one(
            'INSERT INTO usuarios (nombre, apellido, usuario, contrasena, correo) VALUES ($1, $2, $3, $4, $5) RETURNING *',
            [nombre, apellido, usuario, hashedPassword, correo]
        );
        res.json(response);
    }catch (error) {
    console.log("error", error);
    res.status(500).json({ message: error });
  }
}

/* Login de usarios */

const loginUsuario = async (req, res) => {
    try {
        const { usuario, contrasena } = req.body;

         // Buscar al usuario por su nombre de usuario o correo electrónico en la base de datos
         const usuarioEncontrado = await db.oneOrNone('SELECT * FROM usuarios WHERE usuario = $1 OR correo = $1', [usuario]);
         if (!usuarioEncontrado) {
             return res.status(401).json({ message: 'Credenciales inválidas' });
         }

        // Comparar la contraseña proporcionada con la contraseña almacenada
        const contrasenaValida = await bcrypt.compare(contrasena, usuarioEncontrado.contrasena);
        if (!contrasenaValida) {
            return res.status(401).json({ message: 'Credenciales inválidas' });
        }

        // Si las credenciales son válidas, puedes responder con algún tipo de token de autenticación o simplemente una confirmación de éxito
        res.json({ message: 'Inicio de sesión exitoso' });

    } catch (error) {
        console.error('Error al iniciar sesión:', error.message);
        res.status(500).json({ message: 'Error del servidor al iniciar sesión' });
    }
};

module.exports ={
    getCars,
    createUser,
    loginUsuario
}