package uricer.edu.br.minicurso

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.*


class MainActivity : AppCompatActivity() {
    val MAX_PONTOS = 50
    var jogadorAtual = 0
    var pontos = 0
    var pontosJogador = 0
    var pontosComputador = 0
    var player = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iniciarSom()
        iniciarJogo();
    }

    /**
     * Carrega arquivo de som dentro da pasta assets
     * e deixa o áudio 'preparado' para executar
     */
    private fun iniciarSom() {
        val arquivoDeAudio = assets
                .openFd("dice.mp3")
        player.setDataSource(
                arquivoDeAudio.getFileDescriptor(),
                arquivoDeAudio.getStartOffset(),
                arquivoDeAudio.getLength())
        player.prepare()
    }

    /**
     * Inicia pontuações, botões e placar
     */
    fun iniciarJogo() {
        pontosJogador = 0
        pontosComputador = 0
        jogadorAtual = 0
        pontos = 0

        habilitarBotoes()
        atualizarPlacar()
    }

    /**
     * Atualiza o placar conforme a pontuação atual
     * do jogador e do computador
     */
    fun atualizarPlacar() {
        findViewById<TextView>(R.id.tvVoce).setText("Você: " + pontosJogador)
        findViewById<TextView>(R.id.tvComputador).setText("Computador: " + pontosComputador)
    }

    /**
     * Evento de click do botão de rolar dados
     *
     */
    fun rolarDados(view : View) {
        val dado = jogarDado()

        //TODO - lógica está repetida em mais de um lugar
        if(dado == 1) {
            Toast.makeText(this, "Você tirou 1 e passou a vez", Toast.LENGTH_LONG).show()
            jogarComputador()
        } else {
            pontos += dado;
        }
    }

    /**
     * Dispara o som de dados rolando, joga o dado,
     * e atualiza a imagem do dado
     */
    fun jogarDado() : Int {
        tocarSom()
        val dado = getProximoDado()
        mudarImagem(dado)

        return dado
    }

    fun tocarSom() {
        player.seekTo(0)
        player.start()
    }

    /**
     * Obtém um número entre 1 e 6 randomicamente
     */
    fun getProximoDado() : Int {
        val r = Random()
        return r.nextInt(6) + 1;
    }

    /**
     * Altera a imagem do dado, a partir do número randomicamente selecionado
     */
    fun mudarImagem(dado : Int) {
        val imageId = resources.getIdentifier("dice"+dado,"mipmap", packageName);
        val imagemDado = findViewById<ImageView>(R.id.dado)
        imagemDado.setImageResource(imageId);
    }

    /**
     * Passa o turno para o computador, computando os pontos
     */
    fun finalizarTurno(view : View) {
        if(jogadorAtual == 0) {
            pontosJogador += pontos;
        } else {
            pontosComputador += pontos;
        }
        jogarComputador()
    }

    /**
     * Para simular o tempo de jogada dos dados,
     * esse método apenas altera a vez do jogador, e
     * chama depois de 1 segundo a função responsável
     * pela jogada do computador
     */
    fun jogarComputador() {
        proximoJogador();

        Handler().postDelayed({
            computadorIA();
        }, 1000)
    }

    /**
     * IA do computador.
     * Basicamente, tenta sempre jogar mais que 15 pontos,
     * a não ser que os pontos atuais sejam suficientes para ganhar o jogo
     */
    fun computadorIA() {
        val dado = jogarDado()

        if (dado == 1) {
            Toast.makeText(this, "Computador tirou 1 e passou a vez", Toast.LENGTH_LONG).show()
            proximoJogador()
            return
        } else {
            pontos += dado;
        }

        if(pontos < 15 && (pontos + pontosComputador < MAX_PONTOS)) {
            Handler().postDelayed({
                computadorIA();
            }, 1000)
        } else {
            Toast.makeText(this, "Computador fez " + pontos + " pontos", Toast.LENGTH_LONG).show()
            pontosComputador += pontos
            proximoJogador()
        }
    }

    /**
     * Método responsável por trocar o jogador atual,
     * atualizando placar e verificando ganhador
     */
    private fun proximoJogador() {
        atualizarPlacar()

        if(verificaGanhador()) {
            return
        }

        jogadorAtual = (jogadorAtual + 1 ) % 2
        pontos = 0;

        if(jogadorAtual == 0) {
            habilitarBotoes()
        } else {
            desabilitarBotoes()
        }

    }

    private fun verificaGanhador() : Boolean {
        if(pontosJogador >= MAX_PONTOS || pontosComputador >= MAX_PONTOS) {
            var msg = ""
            if(jogadorAtual == 0) {
                msg = "Parabéns! Você Ganhou!!!"
            } else {
                msg = "Você perdeu!!!"
            }
            Toast.makeText(this,
                    msg, Toast.LENGTH_LONG).show()

            desabilitarBotoes()

            return true
        }
        return false
    }

    fun reiniciarJogo (view : View) {
        iniciarJogo()
    }

    private fun desabilitarBotoes() {
        findViewById<Button>(R.id.bJogar).setEnabled(false)
        findViewById<Button>(R.id.bFinalizar).setEnabled(false)
    }

    private fun habilitarBotoes() {
        findViewById<Button>(R.id.bJogar).setEnabled(true)
        findViewById<Button>(R.id.bFinalizar).setEnabled(true)
    }
}
