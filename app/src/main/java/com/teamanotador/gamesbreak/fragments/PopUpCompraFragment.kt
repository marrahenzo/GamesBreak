package com.teamanotador.gamesbreak.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.teamanotador.gamesbreak.EpicGames
import com.teamanotador.gamesbreak.Nakama
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.Steam
import com.teamanotador.gamesbreak.databinding.PopUpCompraBinding
import com.teamanotador.gamesbreak.exceptions.SaldoInsuficienteException
import com.teamanotador.gamesbreak.repositories.GameRepository
import com.teamanotador.gamesbreak.repositories.PurchaseRepository
import com.teamanotador.gamesbreak.repositories.UserRepository

class PopUpCompra : DialogFragment() {

    private lateinit var binding: PopUpCompraBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PopUpCompraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getLong(resources.getString(R.string.game_id))
        val userId = arguments?.getLong(resources.getString(R.string.usuario_id))!!
        //TODO ver de no usar !!
        if (gameId != null) {
            val game = GameRepository.getById(gameId)
            val user = UserRepository.getById(userId)
            binding.subtotal.text = game.getPriceFormateado()
            binding.descuento.text = user.calcularCashback().toString()

            binding.rgIntermediario.setOnCheckedChangeListener { radioGroup, i ->
                val textoBotonSeleccionado =
                    binding.rgIntermediario.findViewById<RadioButton>(i).text
                when (textoBotonSeleccionado) {
                    "Steam" -> {
                        binding.comision.text = Steam.obtenerComision(game).toString()
                        binding.total.text = Steam.obtenerTotal(game, user).toString()
                    }

                    "Epic Games" -> {
                        binding.comision.text = EpicGames.obtenerComision(game).toString()
                        binding.total.text = EpicGames.obtenerTotal(game, user).toString()
                    }

                    "Nakama" -> {
                        binding.comision.text = Nakama.obtenerComision(game).toString()
                        binding.total.text = Nakama.obtenerTotal(game, user).toString()
                    }
                }
            }

            binding.popupBtnComprar.setOnClickListener {
                val idSeleccionado = binding.rgIntermediario.checkedRadioButtonId
                val textoBotonSeleccionado =
                    binding.rgIntermediario.findViewById<RadioButton>(idSeleccionado)?.text
                if (textoBotonSeleccionado != null) {
                    try {
                        val compra = when (textoBotonSeleccionado) {
                            "Steam" -> {
                                Steam.comprar(game, user)
                            }

                            "Epic Games" -> {
                                EpicGames.comprar(game, user)
                            }

                            "Nakama" -> {
                                Nakama.comprar(game, user)
                            }

                            else -> null
                        }

                        PurchaseRepository.add(compra!!)
                        Toast.makeText(activity, R.string.compra_exitosa, Toast.LENGTH_SHORT)
                            .show()
                        activity?.supportFragmentManager?.beginTransaction()?.remove(this)
                            ?.commit()
                    } catch (exception: SaldoInsuficienteException) {
                        Toast.makeText(activity, exception.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                } else
                    Toast.makeText(activity, "no se eligio intermediario", Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}