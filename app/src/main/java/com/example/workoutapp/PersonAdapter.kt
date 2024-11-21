package com.example.workoutapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PersonAdapter(private var personList: List<Person>, private val onItemLongClick: (Person) -> Unit) :
    RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    fun updatePersons(newList: List<Person>) {
        personList = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_person, parent, false)
        return PersonViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = personList[position]
        holder.name.text = person.name
        holder.weight.text = "Weight: ${person.weight} kg"
        holder.bmi.text = "BMI: ${person.bmi}"
        holder.goal.text = "Goal: ${person.goal}"

        holder.itemView.setOnLongClickListener {
            onItemLongClick(person)
            true
        }
    }

    override fun getItemCount(): Int = personList.size

    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.name)
        val weight: TextView = itemView.findViewById(R.id.weight)
        val bmi: TextView = itemView.findViewById(R.id.bmi)
        val goal: TextView = itemView.findViewById(R.id.goal)
    }
}
