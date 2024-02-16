package Planes;

import models.ClassificationLevel;
import models.ExperimentalTypes;

import java.util.Objects;

public class ExperimentalPlane extends Plane {
    private ExperimentalTypes type;
    private ClassificationLevel classificationLevel;

    public ExperimentalPlane(String model, int maxSpeed, int maxFlightDistance, int maxLoadCapacity, ExperimentalTypes type, ClassificationLevel classificationLevel) {
        super(model, maxSpeed, maxFlightDistance, maxLoadCapacity);
        this.type = type;
        this.classificationLevel = classificationLevel;
    }

    public ClassificationLevel getClassificationLevel(){
        return classificationLevel;
    }

    public void setClassificationLevel(ClassificationLevel classificationLevel){
        this.classificationLevel = classificationLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperimentalPlane)) return false;
        ExperimentalPlane otherPlane = (ExperimentalPlane) o;
        return super.equals(otherPlane)
                && this.type == otherPlane.type
                && this.classificationLevel == otherPlane.classificationLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getMaxSpeed(), getMaxFlightDistance(), getMaxLoadCapacity(), type, classificationLevel);
    }

    @Override
    public String toString() {
        return "ExperimentalPlane{" +
                "model='" + getModel() + '\'' +
                ", maxSpeed=" + getMaxSpeed() +
                ", maxFlightDistance=" + getMaxFlightDistance() +
                ", maxLoadCapacity=" + getMaxLoadCapacity() +
                ", type=" + type +
                ", classificationLevel=" + classificationLevel +
                '}';
    }
}
