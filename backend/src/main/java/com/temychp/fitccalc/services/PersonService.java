package com.temychp.fitccalc.services;

import com.temychp.fitccalc.dto.LoginDto;
import com.temychp.fitccalc.dto.PersonDto;
import com.temychp.fitccalc.dto.RegistrationDto;
import com.temychp.fitccalc.dto.UpdatePersonDto;
import com.temychp.fitccalc.models.person.Gender;
import com.temychp.fitccalc.models.person.Person;
import com.temychp.fitccalc.models.person.Role;
import com.temychp.fitccalc.repositories.PersonRepository;
import com.temychp.fitccalc.util.convertors.PersonConvertor;
import com.temychp.fitccalc.util.convertors.RegistrationConvertor;
import com.temychp.fitccalc.util.convertors.UpdatePersonConvertor;
import com.temychp.fitccalc.util.exceptions.AppException;
import com.temychp.fitccalc.util.exceptions.PersonDuplicateException;
import com.temychp.fitccalc.util.exceptions.PersonNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
@Service
public class PersonService {

    private final PersonConvertor personConvertor;

    private final RegistrationConvertor registrationConvertor;

    private final UpdatePersonConvertor updatePersonConvertor;

    private final PersonRepository personRepository;

    private PasswordEncoder passwordEncoder;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findOne(Long id) {
        Optional<Person> foundUser = personRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        enrichPerson(person);
        personRepository.save(person);
    }

    private void enrichPerson(Person person) {
        if (person.getCreatedAt() == null) {
            person.setCreatedAt(Instant.now());
        }
        person.setChangedAt(Instant.now());
        if (person.getRole().getPersonRole().equals("null")) {
            person.setRole(Role.valueOf("USER"));
        }
        person.setPassword(passwordEncoder.encode(person.getPassword()));
    }

    @Transactional
    public void update(UpdatePersonDto updatePersonDto) {
        Long id = updatePersonDto.getId();

        Person person = findOne(id);
        person.setName(updatePersonDto.getName());
        person.setEmail(updatePersonDto.getEmail());
        person.setPassword(updatePersonDto.getPassword());

        save(person);
    }

    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    public Optional<Person> findByName(String name) {
        return personRepository.findByName(name);
    }

    public double getBodyMassIndex(Long id) {
        Person person = findOne(id);
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
        Person person = findOne(id);

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
        Person person = findOne(id);

        int height = person.getPersonAnthropometry().getHeight();

        double result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = height - 100 - ((height - 150) / 4.0);
        } else
            result = height - 100 - ((height - 150) / 2.0);
        return result;
    }

    /**
     * Калькулятор для расчета идеального веса по формуле Devine(1974).
     * для мужчин: вес = 50 + 2.3 * (0.394 * h - 60)
     * для женщин: вес = 45.5 + 2.3 * (0.394 * h - 60), где h - рост, см
     */
    public double getDevine(Long id) {
        Person person = findOne(id);
        int height = person.getPersonAnthropometry().getHeight();

        double result;
        if (person.getPersonAnthropometry().getGender() == Gender.MALE) {
            result = 50 + 2.3 * (0.394 * height - 60);
        } else
            result = 45.5 + 2.3 * (0.394 * height - 60);
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

        Person person = findOne(id);
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

        return result;

    }

    public PersonDto login(LoginDto loginDto) throws PersonNotFoundException, AppException {
        log.info("credentialsDto ={}", loginDto);

        Person person = personRepository.findByName(loginDto.getUsername())
                .orElseThrow(PersonNotFoundException::new);

        log.info("person ={}", person);

        if (passwordEncoder.matches(loginDto.getPassword(), person.getPassword())) {
            return personConvertor.ModelToDto(person);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public PersonDto register(RegistrationDto registrationDto) throws PersonDuplicateException {
        Optional<Person> optionalPerson = personRepository.findByNameOrEmail(registrationDto.getName(), registrationDto.getEmail());

        if (optionalPerson.isPresent()) {
            throw new PersonDuplicateException("Person already exists");
        }

        Person person = registrationConvertor.DtoToModel(registrationDto);

        save(person);

        log.info("Зарегистрирован новый пользователь {}", person);

        return personConvertor.ModelToDto(person);
    }

}