const jwt = require("jsonwebtoken");

// Verificar token de autenticación
exports.verifyToken = (req, res, next) => {
    const tokenWithoutBearer = req.headers.authorization;
    console.log(tokenWithoutBearer)
    if (!tokenWithoutBearer) return res.status(401).json({ message: "Unauthorized" });
    const token = tokenWithoutBearer.replace("Bearer ", "");
    jwt.verify(token, process.env.JWT_SECRET, (err, decoded) => {
        if (err) return res.status(403).json({ message: "Invalid token" });
        req.user = decoded;
        next();
    });
};



// Generar token de autenticación
/*
exports.generateToken = (payload) => {
    return jwt.sign(payload, process.env.JWT_SECRET, { expiresIn: "1h" });
};
*/
