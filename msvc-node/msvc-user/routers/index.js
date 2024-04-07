const { Router } = require("express");

const{
    createUser,
    getCars,
    loginUsuario
} = require("../controllers/constroller-usuario");

const{
    listIngresosEgresos

} = require("../controllers/controller-detalle");
const router = Router();

router.get("/api/v1/:userId/car", getCars)
router.post("/api/v1/usuarios", createUser);
router.post("/api/v1/usuarios", createUser);
router.post("/api/v1/login", loginUsuario);

/Endpoin de Ingresos y Egresos/
router.get("/api/v1/detail/:userId", listIngresosEgresos);

module.exports = router;