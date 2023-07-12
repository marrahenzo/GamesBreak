package com.teamanotador.gamesbreak.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.teamanotador.gamesbreak.EpicGames
import com.teamanotador.gamesbreak.Intermediario
import com.teamanotador.gamesbreak.Nakama
import com.teamanotador.gamesbreak.R
import com.teamanotador.gamesbreak.Steam
import com.teamanotador.gamesbreak.Utils
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
            var intermediario: Intermediario? = null
            binding.subtotal.text = Utils.mostrarMoneyFormateada(game.price)
            binding.descuento.text =
                Utils.mostrarMoneyFormateada(Utils.calcularCashback(user.createdDate))

            binding.rgIntermediario.setOnCheckedChangeListener { _, i ->
                when (binding.rgIntermediario.findViewById<RadioButton>(i).text) {
                    "Steam" -> {
                        binding.comision.text =
                            Utils.mostrarMoneyFormateada(Steam.obtenerComision(game))
                        binding.total.text =
                            Utils.mostrarMoneyFormateada(Steam.obtenerTotal(game, user))
                        intermediario = Steam
                    }

                    "Epic Games" -> {
                        binding.comision.text =
                            Utils.mostrarMoneyFormateada(EpicGames.obtenerComision(game))
                        binding.total.text =
                            Utils.mostrarMoneyFormateada(EpicGames.obtenerTotal(game, user))
                        intermediario = EpicGames
                    }

                    "Nakama" -> {
                        binding.comision.text =
                            Utils.mostrarMoneyFormateada(Nakama.obtenerComision(game))
                        binding.total.text =
                            Utils.mostrarMoneyFormateada(Nakama.obtenerTotal(game, user))
                        intermediario = Nakama
                    }
                }
            }

            binding.popupBtnComprar.setOnClickListener {
                if (intermediario != null) {
                    try {
                        val compra = intermediario!!.comprar(game, user)
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
                    Toast.makeText(activity, R.string.compra_error, Toast.LENGTH_SHORT)
                        .show()
            }
        }
    }
}