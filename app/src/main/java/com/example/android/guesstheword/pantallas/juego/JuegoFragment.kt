/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.pantallas.juego

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.JuegoFragmentBinding

/**
 * Fragment where the game is played
 */
class JuegoFragment : Fragment() {

    // The current word
    private var palabra = ""

    // The current score
    private var puntuacion = 0

    // The list of words - the front of the list is the next word to guess
    private lateinit var listaDePalabras: MutableList<String>

    private lateinit var binding: JuegoFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.juego_fragment,
                container,
                false
        )

        reiniciarLista()
        siguientePalabra()

        binding.loConseguisteButton.setOnClickListener { clickLoConseguiste() }
        binding.omitirButton.setOnClickListener { clickOmitir() }
        actualizarTextoDePuntuacion()
        actualizarTextoDePalabra()
        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */
    private fun reiniciarLista() {
        listaDePalabras = mutableListOf(
                "princesa",
                "hospital",
                "baloncesto",
                "gato",
                "monedas",
                "perro",
                "sopa",
                "calendario",
                "triste",
                "escritorio",
                "guitarra",
                "casa",
                "carretera",
                "elefante",
                "llanta",
                "carro",
                "silla",
                "teléfono",
                "bolsa",
                "botella",
                "arma"
        )
        listaDePalabras.shuffle()
    }

    /**
     * Called when the game is finished
     */
    private fun juegoTerminado() {
        val action = JuegoFragmentDirections.actionGameToScore(puntuacion)
        findNavController(this).navigate(action)
    }

    /**
     * Moves to the next word in the list
     */
    private fun siguientePalabra() {
        //Select and remove a word from the list
        if (listaDePalabras.isEmpty()) {
            juegoTerminado()
        } else {
            palabra = listaDePalabras.removeAt(0)
        }
        actualizarTextoDePalabra()
        actualizarTextoDePuntuacion()
    }

    /** Methods for buttons presses **/

    private fun clickOmitir() {
        puntuacion--
        siguientePalabra()
    }

    private fun clickLoConseguiste() {
        puntuacion++
        siguientePalabra()
    }

    /** Methods for updating the UI **/

    private fun actualizarTextoDePalabra() {
        binding.palabraText.text = palabra

    }

    private fun actualizarTextoDePuntuacion() {
        binding.puntuacionText.text = puntuacion.toString()
    }
}
