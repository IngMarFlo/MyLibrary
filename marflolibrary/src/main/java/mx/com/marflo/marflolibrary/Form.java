package mx.com.marflo.marflolibrary;

import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import mx.com.marflo.marflolibrary.autocomplete_adapter.autocompletesModels;
import mx.com.marflo.marflolibrary.customs_views.AutoCompleteTextViewPlus;
import mx.com.marflo.marflolibrary.customs_views.CheckBoxPlus;
import mx.com.marflo.marflolibrary.customs_views.EditTextPlus;
import mx.com.marflo.marflolibrary.customs_views.RadioButtonPlus;
import mx.com.marflo.marflolibrary.customs_views.RadioGroupPlus;
import mx.com.marflo.marflolibrary.customs_views.SpinnerPlus;
import mx.com.marflo.marflolibrary.customs_views.SwitchPlus;
import mx.com.marflo.marflolibrary.customs_views.TextViewPlus;
import mx.com.marflo.marflolibrary.spinner_adapter.spinnersModels;

/**
 * Clase para obtener los valores ingresados por el usuario en un formulario
 * @author  Alejandro Martínez Flores
 * @since   06 Julio 2018
 * @version 1
 */
public class Form {

    public static void enabledForm(View parent, boolean enabled){
        ViewGroup group = (ViewGroup) parent;

        for (int i = 0; i < group.getChildCount(); i++){
            View v = group.getChildAt(i);

            v.setEnabled(enabled);

            if (v instanceof ViewGroup){
                enabledForm(v, enabled);
            }
        }
    }

    /**
     * Método que verifica cada elemento del formulario y obtiene el valor ingresado o seleccionado por el usuario.
     *
     * @param   parent  Layout padre del archivo xml
     * @return  Objeto  JSONObject con la información ingresada por el usuario
     */
    public static JSONObject getFormValues(View parent){
        if (isValidForm(parent)) {
            try {
                JSONObject js = new JSONObject();
                childsLoop((ViewGroup) parent, js);
                return js;
            }catch (JSONException e){
                return null;
            }
        }else{
            Toast.makeText(parent.getContext(),
                    parent.getContext().getResources().getString(R.string.form_please_complete),
                    Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    /**
     * Método que verifica que los elementos del formulario que hayan sido marcados como obligatorios, estén debidamente ingresados y/o seleccionados
     *
     * @throws RuntimeException Excepción que rompe la aplicación para notificar que el texto del tag no puede convertirse a Json
     * @param parent            Layout padre del archivo xml
     */
    public static boolean isValidForm(View parent){
        return childsLoop((ViewGroup) parent);
    }

    public static void setValues(JSONObject js, View parent){
        ViewGroup top = (ViewGroup) parent;
        try {

            Iterator<String> keys = js.keys();

            while (keys.hasNext()){
                String  k = keys.next();
                View    v = top.findViewWithTag(k);

                if (v instanceof EditTextPlus){
                    set.EditText((EditTextPlus) v, js.getString(k));
                }

                if (v instanceof AutoCompleteTextViewPlus){
                    set.AutoCompleteTextView((AutoCompleteTextViewPlus) v, js.getInt(k));
                }

                if (v instanceof CheckBoxPlus){
                    if (js.getBoolean(k)) {
                        set.CheckBox((CheckBoxPlus) v);
                    }
                }

                if (v instanceof SwitchPlus){
                    if (js.getBoolean(k)) {
                        set.Switch((SwitchPlus) v);
                    }
                }

                if (v instanceof SpinnerPlus){
                    set.Spinner((SpinnerPlus) v, js.getInt(k));
                }

                if (v instanceof TextViewPlus){
                    set.TextView((TextViewPlus) v, js.getString(k));
                }

                if (v instanceof RadioGroupPlus){
                    String val;
                    try {
                        val = (String) js.get(k);
                    }catch (ClassCastException e){
                        Log.println(Log.INFO, "MARFLO_","Excepcion val RadioGroupPlus => "+e.getMessage());
                        val = String.valueOf(js.get(k));
                    }
Log.println(Log.INFO, "MARFLO_","val RadioGroupPlus => "+val);
                    v = parent.findViewWithTag(val);

                    set.RadioButton((RadioButtonPlus) v);
                }

            }

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public static void clear(View parent){
        ViewGroup vg = (ViewGroup) parent;
        for (int i = 0; i < vg.getChildCount(); i++){

            View v = vg.getChildAt(i);

            if (v instanceof TextInputLayout){
                clear.TextInputLayout((TextInputLayout) v);
            }

            if (v instanceof SpinnerPlus){
                clear.Spinner((SpinnerPlus) v);
            }

            if (v instanceof CheckBoxPlus){
                clear.CheckBox((CheckBoxPlus) v);
            }

            if (v instanceof SwitchPlus){
                clear.Switch((SwitchPlus) v);
            }

            if (v instanceof RadioGroupPlus){
                clear.RadioGroup((RadioGroupPlus) v);
            }

            if (v instanceof ViewGroup){
                clear(v);
            }
        }
    }

    private static boolean childsLoop(ViewGroup vg){
        boolean isValid = true;

        for (int i = 0; i<vg.getChildCount(); i++) {

            View v = vg.getChildAt(i);

            if (v instanceof TextInputLayout) {
                TextInputLayout til = (TextInputLayout) v;

                if (til.getEditText() instanceof AutoCompleteTextViewPlus) {
                    if (valid.AutoCompleteTextView((AutoCompleteTextViewPlus) til.getEditText(), til)) {
                        isValid = false;
                    }
                } else {
                    if (valid.EditText((EditTextPlus) til.getEditText(), til)) {
                        isValid = false;
                    }
                }
            }

            if (v instanceof  TextViewPlus){
                if (valid.TextViewPlus((TextViewPlus) v)){
                    isValid = false;
                }
            }

            if (v instanceof SpinnerPlus){
                if (valid.Spinner((SpinnerPlus) v)){
                    isValid = false;
                }
            }

            if (v instanceof RadioGroupPlus){
                if (valid.RadioGroup((RadioGroupPlus) v)){
                    isValid = false;
                }
            }

            if (v instanceof ViewGroup){
                if(!childsLoop((ViewGroup) v)){
                    isValid = false;
                }
            }

        }
        return isValid;
    }

    private static void childsLoop(ViewGroup lyt, JSONObject js) throws JSONException{
        for (int i = 0; i < lyt.getChildCount(); i++){

            View v = lyt.getChildAt(i);

            if (v instanceof TextInputLayout){
                TextInputLayout til =  (TextInputLayout) v;

                if (til.getEditText() instanceof AutoCompleteTextViewPlus){
                    getData.getAutocompleteData((AutoCompleteTextViewPlus) til.getEditText(), js);
                }else {
                    getData.getEditTextData((EditTextPlus) til.getEditText(), js);
                }
            }

            if (v instanceof TextViewPlus){
                getData.getTextViewPlusData((TextViewPlus) v, js);
            }

            if (v instanceof SpinnerPlus){
                getData.getSpinnerData((SpinnerPlus) v, js);
            }

            if (v instanceof CheckBoxPlus){
                getData.getCheckBoxData((CheckBoxPlus) v, js);
            }

            if (v instanceof SwitchPlus){
                getData.getSwitchData((SwitchPlus) v, js);
            }

            if (v instanceof RadioGroupPlus){
                getData.getRadioGroupData((RadioGroupPlus) v, js);
            }

            if (v instanceof ViewGroup){
                childsLoop((ViewGroup) v, js);
            }
        }
    }

    private static class getData{

        private static void getEditTextData(EditTextPlus edt, JSONObject js) throws JSONException{
            js.accumulate(edt.getField(), edt.getText().toString());
        }

        private static void getAutocompleteData(AutoCompleteTextViewPlus edt, JSONObject js) throws JSONException{
            if (edt.getAdapter() == null){
                js.accumulate(edt.getField(), "null adapter");
            }else{
                autocompletesModels m = edt.getSelectedModel();

                if (m != null){
                    js.accumulate(edt.getField(), m.getId());
                    js.accumulate(edt.getField()+"_value", m.getDescription());
                }else {
                    js.accumulate(edt.getField(), "Invalid selection");
                }
            }
        }

        private static void getTextViewPlusData(TextViewPlus tv, JSONObject js) throws JSONException{
            js.accumulate(tv.getField(), tv.getText().toString());
        }

        private static void getSpinnerData(SpinnerPlus spn, JSONObject js) throws JSONException{
            if (spn.getAdapter() == null){
                js.accumulate(spn.getField(), "null adapter");
            }else {
                spinnersModels m = spn.getModel();
                if (m != null) {
                    js.accumulate(spn.getField(), m.getId());
                } else {
                    js.accumulate(spn.getField(), "undefined");
                }
            }
        }

        private static void getCheckBoxData(CheckBoxPlus box, JSONObject js) throws JSONException{
            js.accumulate(box.getField(), box.isChecked());
        }

        private static void getSwitchData(SwitchPlus sw, JSONObject js) throws JSONException{
            js.accumulate(sw.getField(), sw.isChecked());
        }

        private static void getRadioGroupData(RadioGroupPlus rdg, JSONObject js) throws JSONException{
            js.accumulate(rdg.getField(), rdg.getValueOfCheckedRadioButton());
        }
    }

    /**
     * Clase que valida cada tipo de campo de acuerdo a sus propiedades, si el campo ha sido marcado como obligatorio y no ha sido llenado, o
     * seleccionado adecuadamente los métodos regresarán valor true.
     */
    private static class valid{

        private static boolean EditText(EditTextPlus edt, TextInputLayout til){
            if (edt.isObligatorio()){

                if (edt.getText().toString().isEmpty()){
                    if (til != null){
                        til.setError((edt.getInvalidMessage()==null) ? edt.getContext().getResources().getString(R.string.form_mandatory) : edt.getInvalidMessage());
                    }
                    return true;
                }else{
                    if (til != null){
                        til.setError(null);
                    }
                    return false;
                }
            }

            return false;
        }

        private static boolean AutoCompleteTextView(AutoCompleteTextViewPlus edt, TextInputLayout til){
            if (edt.isObligatorio()){
                autocompletesModels m = edt.getSelectedModel();
                if (m != null){
                    if (til != null){
                        til.setError(null);
                    }
                    return false;
                }else{
                    if (til != null){
                        til.setError((edt.getInvalidMessage()==null) ? edt.getContext().getResources().getString(R.string.form_choose_from_list) : edt.getInvalidMessage());
                    }
                    return true;
                }
            }
            return false;
        }

        private static boolean TextViewPlus(TextViewPlus tv){
            if (tv.isObligatorio()){
                return tv.getText().toString().isEmpty();
            }else{
                return false;
            }
        }

        private static boolean Spinner(SpinnerPlus spn){
            if (spn.isObligatorio()){

                if (!spn.hasObligatoryItem()){
                    throw new RuntimeException("SpinnerPlus without id=-1 at 0 position");
                }

                spinnersModels m = spn.getModel();
                if (m != null) {

                    return !m.isSelectionValid();

                } else {
                    throw new RuntimeException("SpinnerPlus without or items");
                }

            }else {
                return false;
            }
        }

        private static boolean RadioGroup(RadioGroupPlus rdg){
            if (rdg.isSelected()){
                return false;
            }else{
                rdg.setError();
                return true;
            }
        }
    }

    private static class set{

        static void EditText(EditTextPlus edt, String t){
            edt.setText(t);
        }

        static void AutoCompleteTextView(AutoCompleteTextViewPlus edt, int id){
            edt.setTextById(id);
        }

        static void Spinner(SpinnerPlus spn, int id){
            spn.setSelectId(id);
        }

        static void CheckBox(CheckBoxPlus box){
            box.setChecked(true);
        }

        static void Switch(SwitchPlus sw){
            sw.setChecked(true);
        }

        static void RadioButton(RadioButtonPlus rdb){
            rdb.setChecked(true);
        }

        static void TextView(TextViewPlus tv, String t){
            tv.setText(t);
        }
    }

    private static class clear{
        static void Spinner(SpinnerPlus spn){
            spn.setSelection(0);
        }

        static void CheckBox(CheckBoxPlus box){
            box.setChecked(false);
        }

        static void Switch(SwitchPlus sw){
            sw.setChecked(false);
        }

        static void RadioGroup(RadioGroupPlus rdb){
            rdb.clearCheck();
        }

        static void TextInputLayout(TextInputLayout til){
            if (til.getEditText() != null) {
                til.getEditText().setText("");
            }
        }
    }
}
