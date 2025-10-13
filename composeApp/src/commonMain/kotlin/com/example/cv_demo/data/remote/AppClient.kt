package com.example.cv_demo.data.remote

import io.ktor.client.plugins.websocket.DefaultClientWebSocketSession
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.websocket.DefaultWebSocketSession

/**
 * A low-level client interface for making HTTP requests.
 * Functions are suspending and return a Result, promoting safe concurrency and error handling.
 */
interface AppClient {

    /**
     * Initiates a WebSocket session.
     *
     * This function establishes a real-time, two-way communication channel with a server endpoint.
     * It's suitable for applications requiring continuous data exchange, such as live updates or chat features.
     *
     * Example usage:
     * ```kotlin
     * client.startWebsocketSession("/live-updates") {
     *     // 'this' is a DefaultClientWebSocketSession
     *     try {
     *         for (frame in incoming) {
     *             when (frame) {
     *                 is Frame.Text -> {
     *                     val receivedText = frame.readText()
     *                     println("Received: $receivedText")
     *                     // Send a response
     *                     send(Frame.Text("Echo: $receivedText"))
     *                 }
     *                 // Handle other frame types (Binary, Close, etc.)
     *             }
     *         }
     *     } catch (e: Exception) {
     *         println("Error during WebSocket session: ${e.message}")
     *     } finally {
     *         println("WebSocket session closed.")
     *     }
     * }
     * ```
     *
     * @param path The WebSocket endpoint path (e.g., "/ws/chat").
     * @param block A suspending lambda with a [DefaultClientWebSocketSession] receiver. This block
     *              defines the behavior for the active session, allowing you to send and receive frames.
     *              The session remains active for the duration of this block's execution.
     * @return A [Result] indicating the outcome of establishing the connection. It returns `Result.success(Unit)`
     *         if the session was established and completed (or closed) without protocol errors during the handshake,
     *         or `Result.failure(Exception)` if the connection could not be established. Note that exceptions
     */
    suspend fun startWebsocketSession(): DefaultClientWebSocketSession
    /**
     * Performs a GET request.
     * @param path The endpoint path (e.g., "/users").
     * @param queryParams A map of query parameters to be appended to the URL.
     * @return A Result containing the HttpResponse on success or an Exception on failure.
     */
    suspend fun get(
        path: String,
        queryParams: Map<String, Any?> = emptyMap()
    ): Result<HttpResponse>

    /**
     * Performs a POST request.
     *
     * This function is used for creating a new resource on the server.
     *
     * Example usage:
     * ```kotlin
     * client.post("/users") {
     *     contentType(ContentType.Application.Json)
     *     setBody(User(name = "Jane Doe", email = "jane.doe@example.com"))
     * }
     * ```
     *
     * @param path The endpoint path (e.g., "/users").
     * @param block A lambda with an [HttpRequestBuilder] receiver for configuring the request,
     *              such as setting the body and headers.
     * @return A [Result] containing the [HttpResponse] on success or an [Exception] on failure.
     */
    suspend fun post(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse>

    /**
     * Performs a PUT request.
     * @param path The endpoint path.
     * @param block A lambda with [HttpRequestBuilder] as its receiver to configure the request,
     *              such as setting the body and headers.
     * @return A [Result] containing the [HttpResponse] on success or an [Exception] on failure.
     */
    suspend fun put(path: String, block: HttpRequestBuilder.() -> Unit): Result<HttpResponse>

    /**
     * Performs a DELETE request.
     * @param path The endpoint path.
     * @return A Result containing the HttpResponse on success or an Exception on failure.
     */
    suspend fun delete(path: String): Result<HttpResponse>
}