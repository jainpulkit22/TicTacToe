package com.example.tictactoe

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    enum class PLAYINGPLAYER{
        FIRST_PLAYER,
        SECOND_PLAYER
    }
    enum class WINNEROFGAME{
        PLAYER_ONE,
        PLAYER_TWO,
        NO_ONE
    }

    // Instances of the above enum classes
    var player: PLAYINGPLAYER? = null
    var winner: WINNEROFGAME? = null

    //Array creation
    var player1Options: ArrayList<Int> = ArrayList()
    var allDisabledImages: ArrayList<ImageButton?> = ArrayList()
    var player2Options: ArrayList<Int> = ArrayList()
    //Name of Players
    var p1: String? = null
    var p2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        p1 = intent.getStringExtra("Player1")
        p2 = intent.getStringExtra("Player2")
        try{
            startService(Intent(this,SoundService::class.java))
        }catch(e: Exception)
        {
            e.printStackTrace()
        }
        player = PLAYINGPLAYER.FIRST_PLAYER
    }
    fun imageButtonTapped(view: View)
    {
        val selectedImageButton: ImageButton= view as ImageButton
        var randomnum = (Math.random()*9 + 1).toInt()
        when(randomnum)
        {
            1 -> constlt1.setBackgroundColor(Color.BLACK)
            2 -> constlt1.setBackgroundColor(Color.YELLOW)
            3 -> constlt1.setBackgroundColor(Color.DKGRAY)
            4 -> constlt1.setBackgroundColor(Color.BLUE)
            5 -> constlt1.setBackgroundColor(Color.CYAN)
            6 -> constlt1.setBackgroundColor(Color.RED)
            7 -> constlt1.setBackgroundColor(Color.GREEN)
            8 -> constlt1.setBackgroundColor(Color.MAGENTA)
            9 -> constlt1.setBackgroundColor(Color.GRAY)
        }
        var operationnumber=0;
        when(selectedImageButton.id)
        {
            R.id.imgbt1 -> operationnumber=1
            R.id.imgbt2 -> operationnumber=2
            R.id.imgbt3 -> operationnumber=3
            R.id.imgbt4 -> operationnumber=4
            R.id.imgbt5 -> operationnumber=5
            R.id.imgbt6 -> operationnumber=6
            R.id.imgbt7 -> operationnumber=7
            R.id.imgbt8 -> operationnumber=8
            R.id.imgbt9 -> operationnumber=9
        }
        action(operationnumber, selectedImageButton)
    }
    private fun action(operationnumber: Int, selectedImageButton: ImageButton)
    {
        if(player == PLAYINGPLAYER.FIRST_PLAYER)
        {
            selectedImageButton.setImageResource(R.drawable.x_letter)
            player1Options.add(operationnumber)
            selectedImageButton.isEnabled=false
            allDisabledImages.add(selectedImageButton)
            player = PLAYINGPLAYER.SECOND_PLAYER
        }
        else if(player == PLAYINGPLAYER.SECOND_PLAYER)
        {
            selectedImageButton.setImageResource(R.drawable.o_letter)
            player2Options.add(operationnumber)
            selectedImageButton.isEnabled=false
            allDisabledImages.add(selectedImageButton)
            player = PLAYINGPLAYER.FIRST_PLAYER
        }
        findthewinnerofgame()
    }
    private fun findthewinnerofgame()
    {
        if(player1Options.contains(1) && player1Options.contains(2) && player1Options.contains(3))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(1) && player2Options.contains(2) && player2Options.contains(3))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(4) && player1Options.contains(5) && player1Options.contains(6))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(4) && player2Options.contains(5) && player2Options.contains(6))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(7) && player1Options.contains(8) && player1Options.contains(9))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(7) && player2Options.contains(8) && player2Options.contains(8))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(1) && player1Options.contains(4) && player1Options.contains(7))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(1) && player2Options.contains(4) && player2Options.contains(7))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(2) && player1Options.contains(5) && player1Options.contains(8))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(2) && player2Options.contains(5) && player2Options.contains(8))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(3) && player1Options.contains(6) && player1Options.contains(9))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(3) && player2Options.contains(6) && player2Options.contains(9))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(1) && player1Options.contains(5) && player1Options.contains(9))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(1) && player2Options.contains(5) && player2Options.contains(9))
            winner = WINNEROFGAME.PLAYER_TWO
        else if(player1Options.contains(3) && player1Options.contains(5) && player1Options.contains(7))
            winner = WINNEROFGAME.PLAYER_ONE
        else if(player2Options.contains(3) && player2Options.contains(5) && player2Options.contains(7))
            winner = WINNEROFGAME.PLAYER_TWO
        if(winner == WINNEROFGAME.PLAYER_ONE)
        {
            createalert(p1+" Won the game", "Congratulations!!", AlertDialog.BUTTON_POSITIVE,"OK", false)
            return
        }
        else if(winner == WINNEROFGAME.PLAYER_TWO)
        {
            createalert(p2+" Won the game", "Congratulations!!", AlertDialog.BUTTON_POSITIVE,"OK", false)
            return
        }
        drawstate()
    }
    private fun createalert(title: String, message: String, whichButton: Int, buttonText: String, cancelable: Boolean)
    {
        var alertDialog: AlertDialog = AlertDialog.Builder(this).create()
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)
        alertDialog.setButton(whichButton, buttonText, {
            dialog: DialogInterface?, which: Int -> resetgame()
        })
        alertDialog.setCancelable(cancelable)
        alertDialog.show()
    }
    private fun resetgame()
    {
        player1Options.clear()
        player2Options.clear()
        allDisabledImages.clear()
        winner = WINNEROFGAME.NO_ONE
        player = PLAYINGPLAYER.FIRST_PLAYER

        imgbt1.setImageResource(R.drawable.place_holder)
        imgbt2.setImageResource(R.drawable.place_holder)
        imgbt3.setImageResource(R.drawable.place_holder)
        imgbt4.setImageResource(R.drawable.place_holder)
        imgbt5.setImageResource(R.drawable.place_holder)
        imgbt6.setImageResource(R.drawable.place_holder)
        imgbt7.setImageResource(R.drawable.place_holder)
        imgbt8.setImageResource(R.drawable.place_holder)
        imgbt9.setImageResource(R.drawable.place_holder)

        imgbt1.isEnabled = true
        imgbt2.isEnabled = true
        imgbt3.isEnabled = true
        imgbt4.isEnabled = true
        imgbt5.isEnabled = true
        imgbt6.isEnabled = true
        imgbt7.isEnabled = true
        imgbt8.isEnabled = true
        imgbt9.isEnabled = true
    }
    private fun drawstate()
    {
        if(allDisabledImages.size == 9 && winner!=WINNEROFGAME.PLAYER_TWO && winner!=WINNEROFGAME.PLAYER_ONE)
        {
            createalert("Draw", "No one won the game!!", AlertDialog.BUTTON_POSITIVE,"OK", false)
            return
        }
    }
}
