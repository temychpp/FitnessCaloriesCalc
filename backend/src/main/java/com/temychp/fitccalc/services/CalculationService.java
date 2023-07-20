package com.temychp.fitccalc.services;

import com.temychp.fitccalc.models.person.Gender;
import com.temychp.fitccalc.models.person.Person;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class CalculationService {

    private final PersonService personService;

    public double getBodyMassIndex(Long id) {
        Person person = personService.findOne(id);
        float weight = person.getPersonAnthropometry().getWeight();
        int height = person.getPersonAnthropometry().getHeight();

        return (double) Math.round(weight / Math.pow(height * 0.01, 2));
    }

    /**
     * Доработанный вариант формулы Миффлина-Сан Жеора
     * для мужчин: (10 x вес (кг) + 6.25 x рост (см) – 5 x возраст (г) + 5) x A;
     * для женщин: (10 x вес (кг) + 6.25 x рост (см) – 5 x возраст (г) – 161) x A.
     * A – уровень активности человека:
     * Минимальная активность (незначительная активность дома, неспешные и
     * краткосрочные пешие прогулки, сидячая офисная работа и т.п.): A = 1,2.
     * Слабая активность (несложные тренировки от 1 до 3 раз в неделю): A = 1,375.
     * Средняя активность (умеренные занятия спортом от 3 до 5 раз в неделю): A = 1,55.
     * Высокая активность (повышенный уровень нагрузки на ежедневных тренировках, 6-7 раз в неделю): A = 1,725.
     * Экстра-активность: (под эту категорию обычно подпадают люди, занимающиеся, например, тяжелой атлетикой,
     * или другими силовыми видами спорта с ежедневными тренировками,
     * те, кто выполняет тяжелую физическую работу, а также те, кто эти два варианта совмещает).A = 1,9
     */
    public int getCaloriesByMifflinStJeorWithActivity(Long id) {
        Person person = personService.findOne(id);

        float weight = person.getPersonAnthropometry().getWeight();
        int height = person.getPersonAnthropometry().getHeight();
        int age = person.getPersonAnthropometry().getAge();
        double activityCoefficient = person.getPersonActivity().getActivityCoefficient().getActivityCoefficientValue();

        int result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = (int) (((10 * weight) + (6.25 * height) - 5 * age + 5) * activityCoefficient);
        } else
            result = (int) (((10 * weight) + (6.25 * height) - 5 * age - 161) * activityCoefficient);

        return result;
    }

    /**
     * Калькулятор для расчета идеального веса по формуле Лоренца(1929).
     * для мужчин: вес = h - 100 - ((h - 150) / 4)
     * для женщин: вес = h - 100 - ((h - 150) / 2), где h - рост, см
     * Наиболее точные результаты показывает для людей старше 18 лет и ростом от 140 до 220 см.
     */
    public double getLorenz(Long id) {
        Person person = personService.findOne(id);

        int height = person.getPersonAnthropometry().getHeight();

        double result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = height - 100 - ((height - 150) / 4.0);
        } else
            result = height - 100 - ((height - 150) / 2.0);

        result = Math.round(result * 100) / 100;

        return result;
    }

    /**
     * Калькулятор для расчета идеального веса по формуле Devine(1974).
     * для мужчин: вес = 50 + 2.3 * (0.394 * h - 60)
     * для женщин: вес = 45.5 + 2.3 * (0.394 * h - 60), где h - рост, см
     */
    public double getDevine(Long id) {
        Person person = personService.findOne(id);
        int height = person.getPersonAnthropometry().getHeight();

        double result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = 50 + 2.3 * (0.394 * height - 60);
        } else
            result = 45.5 + 2.3 * (0.394 * height - 60);

        result = Math.round(result * 100) / 100;

        return result;
    }

    /**
     * Калькулятор для расчета идеального веса по формуле Брока(1871).
     * для мужчин: вес = (h - 100) * 1.15
     * для женщин: вес = (h - 110) * 1.15, где h - рост, см
     * Для возраста младше 30 лет вес уменьшается на 11%, старше 50 лет-увеличивается на 6%.
     * Наиболее точный результат получается при расчете для людей
     * выше 155 и ниже 185 сантиметров и среднего телосложения.
     */

    public double getBroca(Long id) {

        Person person = personService.findOne(id);
        int height = person.getPersonAnthropometry().getHeight();

        double result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = (height - 100) * 1.15;
        } else
            result = (height - 110) * 1.15;

        if (person.getPersonAnthropometry().getAge() < 30) {
            result *= 0.88;
        } else if (person.getPersonAnthropometry().getAge() > 50) {
            result *= 1.06;
        }
        result = Math.round(result * 100) / 100;
        return result;
    }

}