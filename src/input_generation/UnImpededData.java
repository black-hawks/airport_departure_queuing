package input_generation;

public class UnImpededData {
    //variables
    public int season;
    public Double unImpededTime;
    
    //Constructors
    public UnImpededData(){}

    public UnImpededData(int season, Double unImpededTime){
        this.season = season;
        this.unImpededTime = unImpededTime;
    }

    public int getSeason() {
        return season;
    }

    public Double getUnImpededTime() {
        return unImpededTime;
    }
    

}
