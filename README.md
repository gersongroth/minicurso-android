# minicurso-android
Minicurso de Android Semana Acadêmica de Ciência da Computação

Jogo Simples de Dados contra o computador para demonstrar alguns conceitos básicos de android, dentre eles:
- Interface
- Botões/ações
- Mudar imagem
- Alterar texto
- Tocar Som


Regras do Jogo:

É um jogo de dados baseado em turnos, onde jogadores ganham pontos jogando dado e:
• Se rolar um 1, não ganha nenhum ponto e passa a vez
• Se rolar de 2 a 6:
  . Adiciona o valor rolado aos pontos
  . Escolhe rolar novamente os dados ou manter a pontuação atual e passar o turno
O vencedor é o primeiro jogador que chegar ou ultrapassar 100 pontos

Ex:
Um jogador inicia seu turno, joga um dado e obtém o valor 6. Ele decide rolar o dado novamente. Dessa vez, obtém um 4. Ele decide parar. Dessa forma, sua pontuação vai ser acrescida em 10 (6 + 4) e passa seu turno.
Vamos supor que ele tenha tentado mais uma vez. Mas, dessa vez, ele rolou um 1. Obter 1 faz o jogador perder os pontos do turno (mantém o que tinha antes do turno).

Projeto do Android Studio utilizando Android API 28 Plataform
