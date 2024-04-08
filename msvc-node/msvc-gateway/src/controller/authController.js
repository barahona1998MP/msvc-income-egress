const { response } = require("express");
//const { generateToken } = require("../middleware/auth");
const axios = require("axios")

// Método para autenticar al usuario
exports.authenticateUser = async (req, res) => {
    // Lógica para verificar las credenciales del usuario
    // Si las credenciales son válidas, genera un token JWT
    try {
        const response = await axios.post('http://localhost:3000/api/v1' + req.url, req.body);
        //console.log(response)
        res.status(200).json(response.data);
    } catch (error) {
        res.status(404).json(response.data);
    }

    // Genera el token con el payload del usuario
    //const token = generateToken(user);

    // Devuelve el token como respuesta
    //res.json({ token });
};
