package app.ammar.todo.ui.main.view.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.lifecycle.lifecycleScope
import app.ammar.todo.R
import app.ammar.todo.data.model.Todo
import app.ammar.todo.data.model.TodoDatabase
import app.ammar.todo.data.repository.TodoRepository
import app.ammar.todo.databinding.BsAddBinding
import app.ammar.todo.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.util.*
import java.util.Calendar.*


class AddBSDFragment(private val onFinish: () -> Unit) : BottomSheetDialogFragment(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: BsAddBinding

    private var selCalendar = getInstance()

    private var year = 0
    private var month = 0
    private var day = 0
    private var hour = 0
    private var minute = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = BsAddBinding.inflate(inflater)

        with(binding) {
            (requireActivity().intent.getSerializableExtra(EXTRA.TODO) as Todo?)?.let {
                todo = it
                selCalendar.timeInMillis = it.deadline
                addBTN.text = getString(R.string.save)
            }
        }

        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        BottomSheetDialog(requireContext(), R.style.TransparentBSDialog)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(selCalendar) {
            year = get(YEAR)
            month = get(MONTH)
            day = get(DAY_OF_MONTH)
            hour = get(HOUR_OF_DAY)
            minute = get(MINUTE)
        }

        binding.selectDateTV.setOnClickListener {
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }

        binding.selectTimeTV.setOnClickListener {
            TimePickerDialog(
                requireContext(), this, hour, minute, DateFormat.is24HourFormat(context)
            ).show()
        }


        with(binding) {

            checkbox.setOnCheckedChangeListener { _, isChecked ->
                selectDateTV.isEnabled = !isChecked
                selectTimeTV.isEnabled = !isChecked

                if (isChecked) {
                    selCalendar.timeInMillis = tomorrowMillis()
                    displayDateAndTime()

                } else {
                    selCalendar = getInstance()
                    clearDateAndTime()
                }
            }


            addBTN.setOnClickListener {
                if (isValidInputs()) {

                    val newTodo = Todo(
                        title = titleET.text.toString(),
                        desc = descET.text.toString(),
                        deadline = selCalendar.timeInMillis
                    )

                    lifecycleScope.launch {
                        val repo = TodoRepository(TodoDatabase.getDatabase(requireContext()).dao)

                        todo?.run {
                            title = newTodo.title
                            desc = newTodo.desc
                            deadline = newTodo.deadline

                            repo.update(this)
                        }

                        if (todo == null) repo.insert(newTodo)

                        this@AddBSDFragment.dialog!!.dismiss()
                    }

                } else snackbar(root, "Please fill all fields!")
            }

        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        // TODO 03-Jun-21 :-> Discard changes?
        onFinish()
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selCalendar.set(year, month, dayOfMonth)

        displayDate()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        selCalendar.set(HOUR_OF_DAY, hourOfDay)
        selCalendar.set(MINUTE, minute)

        displayTime()
    }


    /**
     * Utility
     */
    private fun displayDateAndTime() {
        displayDate()
        displayTime()
    }

    private fun displayDate() = with(selCalendar.timeInMillis) {
        binding.selectDateTV.text = date()

        if (this < System.currentTimeMillis()) toast(requireContext(), "Deadline passed")
    }

    private fun displayTime() = with(selCalendar.timeInMillis) {
        binding.selectTimeTV.text = time()

        if (this < System.currentTimeMillis()) toast(requireContext(), "Deadline passed")
    }

    private fun clearDateAndTime() = with(binding) {
        listOf(selectDateTV, selectTimeTV).forEach { it.text = "" }
    }


    /**
     * Validation
     */
    private fun isValidInputs(): Boolean = with(binding) {
        listOf(titleET, descET, selectDateTV, selectTimeTV)
            .filter { it.text.isNotBlank() }
            .size == 4
    }

}
