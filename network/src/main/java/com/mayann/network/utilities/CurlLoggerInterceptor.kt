package com.mayann.network.utilities

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset

/**
 * @author May Ann Palencia on 10/13/2023
 * @version 1.0.0
 * @desc Mobile Developer
 * @since 1.0
 */
class CurlLoggerInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        println("\n \n ${generateCurlCommand(request)} \n \n")

        return chain.proceed(request)
    }

    private fun generateCurlCommand(request: Request): String {
        val builder = StringBuilder("")
        builder.apply {
            // command
            append("cURL -g ")
            append("-X ")

            try {
                // method
                append("${request.method.uppercase()} ")
                // header
                request.headers.names().forEach {
                    appendHeader(
                        it,
                        request.headers[it]
                    )
                }
                // request body
                val requestBody = request.body
                appendRequestBody(requestBody)
                // request url
                appendUrl(request)
            } catch (e: Exception) {
                println(e)
            }
        }
        return builder.toString()
    }

    private fun StringBuilder.appendHeader(key: String, value: String?) {
        this.append("""-H "$key: $value" """)
    }

    private fun StringBuilder.appendRequestBody(body: RequestBody?) {
        body?.let {
            val buffer = Buffer()
            var charset: Charset?
            body.writeTo(buffer)
            val contentType = body.contentType()
            contentType?.let { content ->
                this.appendHeader("Content-Type", content.toString())
                charset = content.charset(Charset.forName("UTF-8"))
                this.append(""" -d '${charset?.let { it1 -> buffer.readString(it1) }}' """)
            }
        }
    }

    private fun StringBuilder.appendUrl(request: Request) {
        this.append(""""${request.url}"""")
        this.append(" -L")
    }
}