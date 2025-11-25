package com.example.cv_demo.data.remote.mock

import io.ktor.utils.io.InternalAPI
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketExtension
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class MockWebsocketSession : DefaultWebSocketSession {
    private val channel: Channel<Frame> = Channel()

    override val incoming: ReceiveChannel<Frame>
        get() = channel as ReceiveChannel<Frame>

    override val outgoing: SendChannel<Frame>
        get() = channel as SendChannel<Frame>

    override suspend fun send(frame: Frame) {
        channel.send(frame)
    }


    //Not needed for mocking in/out traffic
    override val closeReason: Deferred<CloseReason?>
        get() = CompletableDeferred(null)
    override var pingIntervalMillis: Long
        get() = 0
        set(value) {}
    override var timeoutMillis: Long
        get() = 0
        set(value) {}
    override val extensions: List<WebSocketExtension<*>>
        get() = emptyList()

    override var masking: Boolean
        get() = false
        set(value) {}
    override var maxFrameSize: Long
        get() = 0
        set(value) {}
    override val coroutineContext: CoroutineContext
        get() = EmptyCoroutineContext

    @InternalAPI
    override fun start(negotiatedExtensions: List<WebSocketExtension<*>>) {
        //Do nothing
    }

    override suspend fun flush() {
        //Do nothing
    }

    override fun terminate() {
        //Do nothing
    }

}