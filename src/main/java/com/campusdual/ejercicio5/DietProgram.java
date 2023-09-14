package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.*;

public class DietProgram {

    public static final String MAXCALORIES = "maxcalories";
    public static final String MAXCARBS = "maxcarbs";
    public static final String MAXFATS = "maxfats";
    public static final String MAXPROTEINS = "maxproteins";


    private Map<String,Diet> dietList;

    private List<Food> foodList;

    private List<Patient> patientList;

    public DietProgram(){
        foodList = new ArrayList<>();
        dietList = new HashMap<>();
    }



    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión de dietas");
            System.out.println("2-Gestión de pacientes");
            System.out.println("3-Salir del programa");
            option = Kb.getOption(1,3);
            switch (option){
                case 1:
                    dietManager();
                    break;
                case 2:
                    patientManager();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 3);
    }

    private void patientManager() {
        System.out.println("########################################################");
        System.out.println("############### Gestión de pacientes ###################");
        System.out.println("########################################################");
    //TODO falta completar el menú de gestión de pacientes
    }
    private Patient getSelectedPatient(){
        if(patientList.isEmpty()){                                 //si no hay pacientes ,muestra el mensaje y sale
            System.out.println("No existen pacientes");
            return null;
        }
        System.out.println("Escoja un paciente de la lista:");

        Integer i = 1;
        for(Patient patient:patientList){
            System.out.println(i+"- "+patient.getName());
            i++;
        }
        System.out.println(i+"- Salir");
        Integer element = Kb.getOption(1,i);
        if(element==i){
            System.out.println("Cancelando alimento");
            return null;
        }else{
            return patientList.get(i);
        }

    }
    private void dietManager() {
        System.out.println("########################################################");
        System.out.println("################# Gestión de Dietas ####################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Agregar dietas");
            System.out.println("2-Gestionar dietas");
            System.out.println("3-Eliminar dieta");
            System.out.println("4-Salir");
             option = Kb.getOption(1,4);
            switch (option){
                case 1:
                    createMenu();
                    break;
                case 2:
                    manageDiet();
                    break;
                case 3:
                    deleteDiet();
                    break;
            }
        }while(option != 4);
    }
    // Reutilizamos el menú de crear dieta


    private void manageDiet() {

        System.out.println("Escoja una dieta para gestionar");
        String selected = getSelectedDiet();    // guardamos la key de la dieta seleccionada
        if (selected == null) {
            System.out.println("Operación cancelada");  // si no seleccionamos una dieta muestra mensaje
        } else {
            Diet selectedDiet = dietList.get(selected);  //obtenemos la dieta de la key seleccionada
            Integer option = null;
            do {
                showDetailsMenu(selectedDiet);  //mostramos la dieta
                System.out.println("1-Cambiar calorias máximas");
                System.out.println("2-Cambiar carbohidratos máximos");
                System.out.println("3-Cambiar grasas máximas");
                System.out.println("4-Cambiar proteinas máximas");
                System.out.println("5-Añadir alimento a la dieta");
                System.out.println("6-Salir");
                option = Kb.getOption(1, 6);
                switch (option) {
                    case 1:
                        updateDiet(selectedDiet, MAXCALORIES);
                        break;
                    case 2:
                        updateDiet(selectedDiet, MAXCARBS);
                        break;
                    case 3:
                        updateDiet(selectedDiet, MAXFATS);
                        break;
                    case 4:
                        updateDiet(selectedDiet, MAXPROTEINS);
                        break;
                    case 5:
                        addFoodMenu(selectedDiet);
                        break;
                }
            } while (option != 6);
        }
    }
    private void updateDiet(Diet selectedDiet, String field){
        System.out.println("Escribe un nuevo valor para el campo");
        Integer newValue = Kb.forceNextInt();
        if(MAXCALORIES.equalsIgnoreCase(field)){
             selectedDiet.setMaxCalories(newValue);
        }else if(MAXCARBS.equalsIgnoreCase(field)){
            selectedDiet.setMaxCarbs((newValue));
        }else if(MAXFATS.equalsIgnoreCase(field)){
            selectedDiet.setMaxFats((newValue));
        }else if(MAXPROTEINS.equalsIgnoreCase(field)){
            selectedDiet.setMaxProteins((newValue));
        }else{
            System.out.println("No existe el campo seleccionado");
        }
    }
    //método que elimina una dieta
    private void deleteDiet() {
        //TODO falta ver que una dieta no está asignada a un cliente
        System.out.println("Selecciona la dieta a eliminar");
        String selected = getSelectedDiet();
        if(selected == null){
            System.out.println("Operación cancelada");  // si no seleccionamos una dieta muestra mensaje
        }else{
            Diet deleted = dietList.remove(selected);  // borra la dieta del Map con la key que le pasamos del método getSelectedDiet()
            System.out.println("Dieta eliminada");
            if(deleted==null){
                System.out.println("No se encontró la dieta a eliminar");
            }
        }
    }

    // método que devuelve la key de una dieta
    private String getSelectedDiet(){
        if(dietList.isEmpty()){                                 //si no hay dietas ,muestra el mensaje y sale
            System.out.println("No existen dietas creadas");
            return null;
        }
        System.out.println("Lista de dietas:");
        Integer i=1;
        List<String> dietOptions = new ArrayList<>(); // se crea una lista para guardar las keys de las dietas
        for(String key:dietList.keySet()){  // se guardan las keys de las dietas en la lista
            dietOptions.add(key);
            System.out.println((i)+"-"+key);
            i++;
        }

        System.out.println((i)+"-Salir");
        Integer selected = Kb.getOption(1,i);
        if(selected == i){
            return null;
        }
        return dietOptions.get(selected-1); // devuelve la key de la dieta escogida

    }

    private void addFoodMenu(Diet updateDiet) {
        if(updateDiet==null){
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = Kb.getOption(1,2);
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(updateDiet,newFood,grams);
                foodList.add(newFood);
                break;
            case 2:
                if(foodList.size()==0){
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"- "+food.getName());
                    i++;
                }
                System.out.println(i+"- Cancelar");
                Integer element = Kb.getOption(1,i);
                if(element==i){
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element-1);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(updateDiet,storedFood,foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Diet updateDiet, Food food, Integer grams){
        try{
            updateDiet.addFood(food,grams);
        }catch (MaxCaloriesReachedException ecal){
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        }catch (MaxCarbsReachedException ecar){
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        }catch (MaxFatsReachedException efat){
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        }catch (MaxProteinsReachedException epro){
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear nueva dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        // pedimos un nombre para la dieta y comprobamos que no exista ya en la lista de dietas
        System.out.println("Escriba un nombre para la dieta");
        String dietName = null;
        boolean dietExists = false;
        do{
            dietName = Kb.nextLine();
            dietExists = dietList.containsKey(dietName);
            if (dietExists){
                System.out.println("El nombre para la dieta ya existe, por favor escriba otro");
            }
        } while(dietExists);
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = Kb.getOption(1,4);
        switch (option){
            case 1:
                dietList.put(dietName,new Diet());
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                dietList.put(dietName,new Diet(calories));
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                dietList.put(dietName,new Diet(fats,carbs,proteins));
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                Diet newDiet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                dietList.put(dietName, newDiet);
                System.out.println("Se ha creado una dieta de "+newDiet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void showDetailsMenu(Diet diet) {
        if(diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if(diet.getMaxCalories()!=null){
                System.out.println("El número máximo de calorías es:"+diet.getMaxCalories());
            }
            if(diet.getMaxCarbs() != null || diet.getMaxFats() != null || diet.getMaxProteins() != null){
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+diet.getMaxCarbs()+" , Grasas:"+diet.getMaxFats()+" , Proteinas:"+diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:"+diet.getFoodNumber());
            System.out.println("Calorías:"+diet.getTotalCalories());
            System.out.println("Carbohidratos:"+diet.getTotalCarbs());
            System.out.println("Grasas:"+diet.getTotalFats());
            System.out.println("Proteínas:"+diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:"+diet.getDietIntakes());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
}
