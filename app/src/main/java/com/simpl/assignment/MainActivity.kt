package com.simpl.assignment

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.simpl.assignment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    /*
    0 dot ball
    1 single
    4 is four
    6 is six
    -1 is out
    */
    var targetScore:Int=40
    var targetWickets:Int=3
    var totalBalls:Int=24

    var scorecard:Int=0
    var wickets:Int=0
    var overs:Int=0
    var ballsComplteted=0
    var usedPlayers:Int=0
    var strikePlayer:Player?=null
    var nonStrikePlayer:Player?=null

    private lateinit var binding: ActivityMainBinding

    var playerList=ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //get the player probability data
        playerList=DataLayer().playerDataLoad();


        //Initialize the the strike player and non striker
        strikePlayer=playerList[0]
        nonStrikePlayer=playerList[1]
        usedPlayers=1;
        defauatUI(0)

        binding.BtnStrike.setOnClickListener(){

            ballsComplteted++
            updateScore(Random.getRandomScore(strikePlayer?.frequency))
        }

        binding.BtnReset.setOnClickListener(){
            scorecard=0
            wickets=0
            overs=0
            ballsComplteted=0
            usedPlayers=0
            strikePlayer=null
            nonStrikePlayer=null
            playerList=DataLayer().playerDataLoad();
            strikePlayer=playerList[0]
            nonStrikePlayer=playerList[1]
            usedPlayers=1;
            defauatUI(0)
            binding.BtnStrike.isEnabled=true
            binding.BtnReset.visibility=View.GONE
            binding.tvResult.visibility=View.GONE
        }
    }



    fun updateScore(score:Int){

        if(score==-1){
            wickets++
            usedPlayers++
            strikePlayer=if(usedPlayers<=targetWickets) playerList.get(usedPlayers) else Player()

        }
        else{
            scorecard += score;
            strikePlayer?.score=strikePlayer?.score!!+score
            if(score%2==0){

            }
            else{
                swapPlayer(strikePlayer,nonStrikePlayer)
            }
        }

        if(ballsComplteted%6==0){
            swapPlayer(strikePlayer,nonStrikePlayer)
            overs+=1
        }
        updateUI(score)

    }

    private fun swapPlayer(strikePlayer: Player?, nonStrikePlayer: Player?) {
            val temp=strikePlayer
            this.strikePlayer =nonStrikePlayer
            this.nonStrikePlayer =temp
    }

    private fun updateUI(score:Int) {
        Log.d("srini","wickets $wickets  target wickets $targetWickets  used play$usedPlayers")

        binding.tvScore.text="Score: $scorecard - $wickets"
        binding.tvRuns.text=if (score==-1) "OUT" else score.toString()
        binding.tvLabel.text=if (score==-1) "" else "Runs"
        binding.tvOvers.text="Overs: ${overs}.${ballsComplteted%6}"
        binding.tvStriker.text="${strikePlayer?.playerName} (${strikePlayer?.score})*"
        binding.tvNonStriker.text="${nonStrikePlayer?.playerName} (${nonStrikePlayer?.score})"

        if(scorecard>=targetScore){
            restartGameEnabled()

            binding.tvResult.text =
                    "Bengaluru won the match with ${targetWickets - wickets} wickets and ${totalBalls - ballsComplteted} balls"

        }
        else if(wickets==targetWickets){
            restartGameEnabled()
            binding.tvResult.text = "Bengaluru lost the match with ${targetScore-scorecard} runs"

        }
        else if(ballsComplteted==totalBalls){
            restartGameEnabled()
            if(scorecard==(targetScore-1)){
                binding.tvResult.text = "Match Tied"
            }
            else{

                binding.tvResult.text = "Bengaluru lost the match with ${targetScore-scorecard} runs"
            }
        }

    }

    private fun defauatUI(score:Int) {
        binding.tvScore.text="Score: $scorecard - $wickets"
        binding.tvOvers.text="Overs: ${overs}.${ballsComplteted%6}"
        binding.tvStriker.text="${strikePlayer?.playerName} (${strikePlayer?.score})*"
        binding.tvNonStriker.text="${nonStrikePlayer?.playerName} (${nonStrikePlayer?.score})"
        binding.tvRuns.text=""
        binding.tvLabel.text=""
    }

    private fun restartGameEnabled(){
        binding.tvResult.visibility= View.VISIBLE
        binding.BtnStrike.isEnabled=false
        binding.BtnReset.visibility= View.VISIBLE
    }




}