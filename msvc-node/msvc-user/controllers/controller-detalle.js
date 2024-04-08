//const { db } = require("../config/cnn");
const axios = require('axios');


/Listar Ingresos y Egresos por usuario/

const listIngresosEgresos = async (req, res) => {
    try {
        const userId = req.params.userId;
        console.log(userId);
        const response = await axios.get(`http://localhost:8082/api/detail/${userId}/user`);
        console.log(response);
        const lisDetalle = response.data; 
    res.json(lisDetalle)
    } catch (error) {
        res.status(500).send('Error retrieving detail for user');
    }
}

module.exports ={
    listIngresosEgresos
}