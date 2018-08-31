package uricer.edu.br.minicurso.kotlin

data class Float3(val x : Float = 0f,
                  val y : Float = 0f,
                  val z : Float = 0f) {
    operator fun plus(v: Float3) =
            Float3(x + v.x,
                    y + v.y,
                    z + v.z
            )
}

val inicio = Float3(y = 3f)
val fim = Float3(x = 0.5f, z = 1.5f)

fun main(args: Array<String>) {
    print(inicio + fim)
}
