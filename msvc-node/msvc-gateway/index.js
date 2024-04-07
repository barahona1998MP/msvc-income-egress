const express = require("express");
const app = express();
const gatewayRoutes = require("./routes/gatewayRoutes");
const authRoutes = require("./routes/authRoutes");
require("dotenv").config();

// Middlewares
app.use(express.json());


// Routes
app.use("/api", gatewayRoutes);
app.use("/auth", authRoutes);

const PORT = process.env.PORT || 4000;
app.listen(PORT, () => {
    console.log(`Server is running on port http://localhost:${PORT}`);
});
