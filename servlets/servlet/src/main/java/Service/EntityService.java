package Service;

import com.example.Entity;

import java.util.Calendar;

public class EntityService {

    Calendar calendar = Calendar.getInstance();

    public Entity entityOnEveryDay (){
        Entity entitySwitch;
        switch (calendar.get(Calendar.DAY_OF_WEEK)){
            case (1) :
                entitySwitch = new Entity("Если нет ветра, беритесь за вёсла.","Jason Stathem");
                break;

            case (2) :
                entitySwitch = new Entity("Надо любить жизнь больше, чем смысл жизни","Jason Stathem");
                break;
            case (3) :
                entitySwitch = new Entity("Молчание - лучший способ ответа на бессмысленные вопросы","Jason Stathem");
                break;
            case (4) :
                entitySwitch = new Entity(" Логика может привести Вас от пункта А к пункту Б, а воображение — куда угодно. "
                        ,"Jason Stathem");
                break;
            case (5) :
                entitySwitch = new Entity("Начинать всегда стоит с того, что сеет сомнения ","Jason Stathem");
                break;
            case (6) :
                entitySwitch = new Entity("Кто обзывается, тот сам так называется","Jason Stathem");
                break;
            case (7) :
                entitySwitch = new Entity("80% успеха - это появиться в нужном месте в нужное время","Jason Stathem");
                break;

            default:
                entitySwitch = new Entity( "Счастье – это не нечто готовое. Счастье зависит только от ваших действий. "
                        + "text","Jason Stathem");
        }
        return entitySwitch;
    }
}
