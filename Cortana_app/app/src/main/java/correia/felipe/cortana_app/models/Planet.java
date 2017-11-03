package correia.felipe.cortana_app.models;

/**
 * Created by Felipe on 02/11/2017.
 */

public class Planet {

    public String name;
    public String rotation_period;
    public String orbital_period;
    public String diameter;
    public String climate;
    public String gravity;
    public String terrain;
    public String surface_water;
    public String population;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getRotationPeriod() {
        return rotation_period;
    }

    public void setRotationPeriod(String rotation_period) {
        this.rotation_period = rotation_period;
    }

    public String getOrbitalPeriod() {
        return orbital_period;
    }

    public void setOrbitalPeriod(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getSurfaceWater() {
        return surface_water;
    }

    public void setSurfaceWater(String surface_water) {
        this.surface_water = surface_water;
    }

}
