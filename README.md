# Sistema de Pedidos y Reparto - Mercado Hogar

## Descripción del Proyecto
Este proyecto consiste en el desarrollo del backend de un sistema de pedidos y reparto para el supermercado "Mercado Hogar", el cual comercializa alimentos y productos de consumo cotidiano. El sistema permite gestionar un catálogo de productos, inventario, carrito de compras, pedidos, pagos, despachos, devoluciones y reportes, solucionando los problemas actuales de administración manual y descentralizada de la empresa.

La solución está construida sobre una **Arquitectura de Microservicios** utilizando **Spring Boot**, integrando base de datos relacional (JPA/Hibernate), validaciones, manejo centralizado de excepciones y documentación formal (Swagger/OpenAPI).

## Nombre estudiante
- Diego Navarrete

## Listado de Microservicios Implementados
1. **api-gateway**: Punto de entrada centralizado para todas las solicitudes (Spring Cloud Gateway).
2. **usuario-service**: Gestión de autenticación, roles, clientes y personal.
3. **producto-service**: Catálogo, búsqueda y administración de productos.
4. **inventario-service**: Control de stock, movimientos, ajustes y alertas.
5. **carrito-service**: Gestión de los productos que el cliente desea comprar.
6. **pedido-service**: Gestión del ciclo de vida de los pedidos.
7. **pago-service**: Procesamiento de transacciones.
8. **reparto-service**: Direcciones de entrega, asignación de repartidores y seguimiento.
9. **promocion-service**: Gestión y validación de descuentos y códigos promocionales.
10. **devolucion-service**: Reclamos, solicitudes de devolución y reembolsos.
11. **reporte-service**: Generación de métricas de ventas y operaciones.
