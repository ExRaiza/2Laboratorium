
class Figura{
	public enum K1{
		Kolo(1){
			double obliczPole(){
				return Math.PI*a*a;
			}
			double obliczObwod(){
				return 2*Math.PI*a;
			}
		},
		Kwadrat(1){
			double obliczPole(){
				return a*a;
			}
			double obliczObwod(){
				return 4*a;
			}
		}, Pieciokat(1){
			double obliczPole(){
				return (a*a*(Math.sqrt(5*(5+2*Math.sqrt(5)))))/4;
			}
			double obliczObwod(){
				return 5*a;
			}
		}, Szesciokat(1){
			double obliczPole(){
				return (3*a*a*Math.sqrt(3))/2;
			}
			double obliczObwod(){
				return 6*a;
			}
		};
		double a;
		K1(double a){
			this.a = a;
		}
		double obliczPole(){
			return a;
		}
		double obliczObwod(){
			return a;
		}
	}
	public enum K2{
		Prostokat(1,1,90){
			double obliczPole(){
				return a*b;
			}
			double obliczObwod(){
				return 2*a+2*b;
			}
		}, Romb(1,1,30){
			double obliczPole(){
				return a*a*Math.sin(Math.toRadians(kat));
			}
			double obliczObwod(){
				return 4*a;
			}
		};
		double a, b, kat;
		K2(double a, double b,double kat){
			this.a = a;
			this.b = b;
			this.kat = kat;
		}
		double obliczPole(){
			return a;
		}
		double obliczObwod(){
			return a;
		}
	}
}
