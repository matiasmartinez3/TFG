package com.example.travelwithmeapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.travelwithmeapp.R
import com.example.travelwithmeapp.models.Plan

class PlanificarAdapter(private val context: Context, private val listaPlanes: ArrayList<Plan>) :
    RecyclerView.Adapter<PlanificarAdapter.PlanViewHolder>() {

    inner class PlanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Parseo datos recyclerview
        val horaTextView: TextView = itemView.findViewById(R.id.text_hora_plan) // datos texto viewholder
        val nombreTextView: TextView = itemView.findViewById(R.id.text_nombre_plan)
        val descripcionTextView: TextView = itemView.findViewById(R.id.text_descripcion_plan)
        val precioTextView: TextView = itemView.findViewById(R.id.text_precio_plan)

        // Gestión de visibilidad y de pulsación de icono_fav del fragment planes
        val iconoRellenoFav: ImageView = itemView.findViewById(R.id.icono_relleno_fav)
        val iconoVacioFav: ImageView = itemView.findViewById(R.id.icono_vacio_fav)

        init {
            iconoRellenoFav.setOnClickListener {
                iconoRellenoFav.visibility = View.INVISIBLE
                iconoVacioFav.visibility = View.VISIBLE

                val plan = listaPlanes[adapterPosition]
                onFavoritoClickListener?.onFavoritoClick(plan)
            }
            iconoVacioFav.setOnClickListener {
                iconoVacioFav.visibility = View.INVISIBLE
                iconoRellenoFav.visibility = View.VISIBLE

                val plan = listaPlanes[adapterPosition]
                onFavoritoClickListener?.onFavoritoClick(plan)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.viewholder_planificar, parent, false)
        return PlanViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlanViewHolder, position: Int) {
        val currentPlan = listaPlanes[position]
        holder.horaTextView.text = currentPlan.horaPlan
        holder.nombreTextView.text = currentPlan.nombrePlan
        holder.descripcionTextView.text = currentPlan.descripcionPlan
    }

    override fun getItemCount(): Int {
        return listaPlanes.size
    }

    interface OnFavoritoClickListener {
        fun onFavoritoClick(plan: Plan)
    }
    var onFavoritoClickListener: OnFavoritoClickListener? = null
}