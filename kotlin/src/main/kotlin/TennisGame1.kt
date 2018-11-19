class TennisGame1(private val player1Name: String, private val player2Name: String) : TennisGame {

    private val scores = mutableMapOf(player1Name to 0, player2Name to 0)

    override fun wonPoint(playerName: String){
        scores[playerName]?.let {
            scores.plusAssign(playerName to (it + 1))
        }
    }

    override fun getScore(): String {
        val player1Score = scores[player1Name]!!
        val player2Score = scores[player2Name]!!
        return when {
            player1Score == player2Score -> drawToTennisScore(player1Score)
            player1Score >= 4 || player2Score >= 4 -> deuceToTennisScore(player1Score - player2Score)
            else -> pointsToTennisScore(player1Score) + "-" + pointsToTennisScore(player2Score)
        }
    }
}

private fun pointsToTennisScore(score: Int): String = when (score) {
    0 -> "Love"
    1 -> "Fifteen"
    2 -> "Thirty"
    else -> "Forty"
}

private fun drawToTennisScore(points: Int): String = when (points) {
    0 -> "Love-All"
    1 -> "Fifteen-All"
    2 -> "Thirty-All"
    else -> "Deuce"
}

private fun deuceToTennisScore(minusResult: Int): String = when {
    minusResult == 1 -> "Advantage player1"
    minusResult == -1 -> "Advantage player2"
    minusResult >= 2 -> "Win for player1"
    else -> "Win for player2"
}
