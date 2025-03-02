package ru.nsu.fit.endpoint.manager;

import org.apache.commons.lang.NotImplementedException;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import ru.nsu.fit.endpoint.database.IDBService;
import ru.nsu.fit.endpoint.database.data.PlanPojo;

import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlanManager extends ParentManager {
    public PlanManager(IDBService dbService, Logger flowLog) {
        super(dbService, flowLog);
    }

    /**
     * Метод создает новый объект типа Plan. Ограничения:
     * name - длина не больше 128 символов и не меньше 2 включительно не содержит спец символов. Имена не пересекаются друг с другом;
    /* details - длина не больше 1024 символов и не меньше 1 включительно;
    /* fee - больше либо равно 0 но меньше либо равно 999999.
     */
    public PlanPojo createPlan(PlanPojo plan) {

        String name = plan.name;

        Validate.isTrue((name.length() <= 128 && name.length() >= 2), "Name is bigger than 128 or less than 2.");
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(name);
        Validate.isTrue(!m.find(), "Name contains a special character.");




        return dbService.createPlan(plan);
    }

    public PlanPojo updatePlan(PlanPojo plan) {
        throw new NotImplementedException("Please implement the method.");
    }

    public void removePlan(UUID id) {
        throw new NotImplementedException("Please implement the method.");
    }

    /**
     * Метод возвращает список планов доступных для покупки.
     */
    public List<PlanPojo> getPlans(UUID customerId) {
        throw new NotImplementedException("Please implement the method.");
    }
}
