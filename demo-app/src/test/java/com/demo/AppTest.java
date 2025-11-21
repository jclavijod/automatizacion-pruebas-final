package com.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AppTest {

    @Test
void testApp() {
    // Esto hará fallar el test a propósito
    assertTrue(false, "Forzando fallo de prueba para verificar CI");
}
}
// Comentario para disparar CI