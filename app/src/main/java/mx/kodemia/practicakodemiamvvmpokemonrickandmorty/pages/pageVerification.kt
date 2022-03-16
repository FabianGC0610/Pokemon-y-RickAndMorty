package mx.kodemia.practicakodemiamvvmpokemonrickandmorty.pages

import android.widget.Button

fun pageVerificacation (page: Int, buttonNext: Button, buttonReturn: Button){
    if(page == 1){
        buttonReturn.isEnabled = false
    }else if (page == 42){
        buttonNext.isEnabled = false
    }else{
        buttonReturn.isEnabled = true
        buttonNext.isEnabled = true
    }
}