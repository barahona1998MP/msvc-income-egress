const express = require("express");
const router = express.Router();
const { authenticateUser } = require("../controller/authController");

// Ruta para la autenticación de usuarios
router.post("/login", authenticateUser);

module.exports = router;
