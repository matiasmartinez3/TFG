package com.example.travelwithmeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.travelwithmeapp.R
import com.example.travelwithmeapp.databinding.FragmentBuscarBinding
import com.example.travelwithmeapp.utils.Utilities


class BuscarFragment : Fragment() {
    private lateinit var binding: FragmentBuscarBinding
    private var backgroundActual: ImageView? = null
    //es un int, que va a determinar si el usuario quiere buscar vuelos (1), hoteles (2), o no ha seleccionado nada (0)
    private var busquedaFlag = 0
    private lateinit var utilities: Utilities



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBuscarBinding.inflate(inflater, container, false)

        inicializar()

        return binding.root

    }

    fun inicializar() {
        utilities = Utilities()
        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutVuelos, 0)
        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutHoteles, 0)

        binding.toggleButton.addOnButtonCheckedListener { toggleButtonGroup, checkedId, isChecked ->
            if(isChecked) {
                when(checkedId) {
                    binding.buttonVuelos.id -> {
                        cambiarFondo("vuelos")
                        busquedaFlag = 1
                        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutVuelos, 1)
                        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutHoteles, 0)

                    }
                    binding.buttonHoteles.id -> {
                        cambiarFondo("hoteles")
                        busquedaFlag = 2
                        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutHoteles, 1)
                        cambiarVisibilidadChildrenViewGroup(binding.constraintLayoutVuelos, 0)
                    }
                }
            }
        }
        binding.fechaVuelos.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                utilities.lanzarDatePickerDialog(view, requireContext())
            }
        }
        binding.fechaEntradaHotel.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                utilities.lanzarDatePickerDialog(view, requireContext())
            }
        }
        binding.fechaSalidaHotel.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                utilities.lanzarDatePickerDialog(view, requireContext())
            }
        }


        binding.buttonComenzar.setOnClickListener() {
            when(busquedaFlag) {
                1 ->  findNavController()?.navigate(R.id.action_buscarFragment_to_buscarVuelosFragment)
                2 -> findNavController()?.navigate(R.id.action_buscarFragment_to_buscarHotelesFragment)
            }

        }


        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
       binding.toolbar.setNavigationOnClickListener() {
           requireActivity().onBackPressedDispatcher.onBackPressed()
       }

    }

    fun cambiarVisibilidadChildrenViewGroup(viewGroup: ViewGroup, funcionalidad: Int) {
        when(funcionalidad) {
            0 -> viewGroup.visibility = View.GONE
            1 -> viewGroup.visibility = View.VISIBLE
        }
        for(i in 0 until viewGroup.childCount) {
            val child = viewGroup.getChildAt(i)
            when(funcionalidad) {
                0 -> child.visibility = View.GONE
                1 -> child.visibility = View.VISIBLE
            }
        }

    }

    fun cambiarFondo(tipo: String) {
        var imagen: ImageView? = null
        when(tipo) {
            "hoteles" -> imagen = binding.backgroundHoteles
            "vuelos" -> imagen = binding.backgroundVuelos
        }

        imagen?.let {
            backgroundActual?.let {
                //hago que la imagen que quiero que sea el fondo, se superponga al fondo que había antes
                imagen.elevation = backgroundActual!!.elevation
                backgroundActual!!.elevation = backgroundActual!!.elevation -1
            }

            //la imagen aparece con un fade in
            val fadeIn = AlphaAnimation(0f, 1f)
            fadeIn.duration = 1000 // Duración de la animación en milisegundos
            imagen.startAnimation(fadeIn)

            //cuando ha terminado el fade in, hago la imagen visible
            imagen.visibility = View.VISIBLE

            //convierto la imagen que he hecho visible al background actual
            backgroundActual = imagen
        }

    }





}


