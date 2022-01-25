package StepTracker;


import java.util.HashMap;

public class StepTracker {
    HashMap<String, MonthData> stepsInMonth = new HashMap<>();
    Converter converter = new Converter();
    int target = 10000;

    StepTracker(){
        stepsInMonth.put("Январь", new MonthData());
        stepsInMonth.put("Февраль", new MonthData());
        stepsInMonth.put("Март", new MonthData());
        stepsInMonth.put("Апрель", new MonthData());
        stepsInMonth.put("Май", new MonthData());
        stepsInMonth.put("Июнь", new MonthData());
        stepsInMonth.put("Июль", new MonthData());
        stepsInMonth.put("Август", new MonthData());
        stepsInMonth.put("Сентябрь", new MonthData());
        stepsInMonth.put("Октябрь", new MonthData());
        stepsInMonth.put("Ноябрь", new MonthData());
        stepsInMonth.put("Декабрь", new MonthData());

    }

    boolean addSteps(String month, int day, int steps){

        if (stepsInMonth.containsKey(month) && day > 0 && day <= 30 && steps > 0){
            stepsInMonth.get(month).addSteps(steps, day);

            return true;
        }
        return false;
    }

    void statistics(String month){

        if (!stepsInMonth.containsKey(month)){
            System.out.println("Извините, такого месяца не существует.");
            return;
        }

        MonthData data = stepsInMonth.get(month);

        int maxSteps = 0;
        int sum = 0;
        for (int i = 0; i < 30; i++){
            System.out.print(i + 1 + " - й день: " + data.days[i]);
            if (i != 29){
                System.out.print(", ");
            }

            sum += data.days[i];
            maxSteps = Integer.max(maxSteps, data.days[i]);
        }

        System.out.println("Общее количество шагов за месяц: " + sum);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);
        System.out.println("Среднее количество шагов: " + sum / 30);
        System.out.println("Пройденное расстояние: " + converter.stepsToDistance(sum) / 100000 + "км");
        System.out.println("Количество сожжённых килокалорий: " + converter.stepsToCalories(sum) / 1000);

        int bestSeries = 0;
        int firstDayOfBestSeries = 0;
        int currentSeries = 0;
        int firstDayOfCurrentSeries = 0;
        boolean currentSeriesContinue = false;
        for (int i = 0; i < data.days.length; i++) {
            if (data.days[i] >= target) {
                if (!currentSeriesContinue) {
                    currentSeriesContinue = true;
                    firstDayOfCurrentSeries = i;
                    currentSeries++;
                }
            } else {
                currentSeriesContinue = false;
                if (bestSeries < currentSeries) {
                    bestSeries = currentSeries;
                    firstDayOfBestSeries = firstDayOfCurrentSeries;
                }
            }
        }

        if (bestSeries < currentSeries) {
            bestSeries = currentSeries;
            firstDayOfBestSeries = firstDayOfCurrentSeries;
        }
        System.out.println("Ваша лучшая серия: " + bestSeries + " дней.");
        for (int i = firstDayOfBestSeries; i < firstDayOfBestSeries + bestSeries; i++) {
            System.out.print(i+1 + "-й день: " + data.days[i]);
            if (i != bestSeries - 1) {
                System.out.print(", ");
            }
        }
    }




    


    class MonthData {

        public int[] days = new int[30];


        void addSteps(int steps, int day){
            days[day - 1] = steps;
        }
    }

    public void setTarget(int target) {
        this.target = target;
    }

}
