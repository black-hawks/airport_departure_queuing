/**

The UnImpededData class represents a data object that contains the season and unimpeded time.
The season and unimpeded time can be accessed through getters and can be set through the constructor.
*/

package input_generation;

/**
 * @author ravi
 *
 */

public class UnImpededData {
	// Instance variables
	public int season;
	public Double unImpededTime;

	/**
	 * Constructs an empty UnImpededData object.
	 */
	public UnImpededData(){}

	/**
	 * Constructs an UnImpededData object with the given season and unimpeded time.
	 * 
	 * @param season the season of the data
	 * @param unImpededTime the unimpeded time of the data
	 */
	public UnImpededData(int season, Double unImpededTime){
	    this.season = season;
	    this.unImpededTime = unImpededTime;
	}

	/**
	 * Returns the season of the UnImpededData object.
	 * 
	 * @return the season of the data
	 */
	public int getSeason() {
	    return season;
	}

	/**
	 * Returns the unimpeded time of the UnImpededData object.
	 * 
	 * @return the unimpeded time of the data
	 */
	public Double getUnImpededTime() {
	    return unImpededTime;
	}

}
