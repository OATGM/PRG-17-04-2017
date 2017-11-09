use piston_window::*;
use opengl_graphics::GlGraphics;
use object::Object;
use food::Food;
use std::mem::swap;

pub struct Snake{
	anchs: Vec<(f64,f64)>,
	dir: i64,
	nextdir: i64,
	game_size: (f64, f64),
	size: f64,
	pub grow: f64,
	pub speed: f64,
}

impl Snake{
	pub fn new(end: (f64, f64), pos: (f64, f64), game_size: (f64, f64), size: f64, speed: f64) -> Snake{
		let mut snake = Snake{
			dir: 2,
			nextdir: 2,
			game_size: game_size,
			size: size,
			grow: 0.,
			speed: speed,
			anchs: Vec::new(),
		};
		snake.anchs.push(end);
		snake.anchs.push(pos);
		snake
	}

	fn body_coll(&self) -> bool{
		if self.anchs.len() < 4 {return false;}

		let head = self.anchs[self.anchs.len()-1];
		let head2 = self.anchs[self.anchs.len()-2];

		for i in 0..self.anchs.len()-3{
			let body = self.anchs[i];
			let body2 = self.anchs[i+1];

			let sz2 = self.size*2.;

			if (head.0-head2.0).abs() <= 1.{
				if (body.0-body2.0).abs() <= 1.{
					if (head.0-body.0).abs() < sz2{
						if head.1 >= body.1 && head.1 >= body2.1 && head2.1 >= body.1 && head2.1 >= body2.1{}
						else if head.1 <= body.1 && head.1 <= body2.1 && head2.1 <= body.1 && head2.1 <= body2.1{}
						else {return true;}
					}
				}else{
					if (head.1 > body.1 - sz2 && head2.1 < body.1 + sz2) ||
							(head.1 < body.1 + sz2 && head2.1 > body.1 - sz2){
						if head.0 < body.0+sz2 && head2.0 < body.0-sz2 && head.0 < body2.0+sz2 && head2.0 < body2.0-sz2{}
						else if head.0 > body.0+sz2 && head2.0 > body.0-sz2 && head.0 > body2.0+sz2 && head2.0 > body2.0-sz2{}
						else {return true;}
					}
				}
			}else if (head.1-head2.1).abs() <= 1.{
				if (body.1-body2.1).abs() <= 1.{
					if (head.1-body.1).abs() < sz2{
						if head.0 >= body.0 && head.0 >= body2.0 && head2.0 >= body.0 && head2.0 >= body2.0{}
						else if head.0 <= body.0 && head.0 <= body2.0 && head2.0 <= body.0 && head2.0 <= body2.0{}
						else {return true;}
					}
				}else{
					if (head.0 > body.0 - sz2 && head2.0 < body.0 + sz2) ||
							(head.0 < body.0 + sz2 && head2.0 > body.0 - sz2){
						if head.1 < body.1+sz2 && head2.1 < body.1-sz2 && head.1 < body2.1+sz2 && head2.1 < body2.1-sz2{}
						else if head.1 > body.1+sz2 && head2.1 > body.1-sz2 && head.1 > body2.1+sz2 && head2.1 > body2.1-sz2{}
						else {return true;}
					}
				}
			}
		}
		false
	}

	pub fn test_die(&self) -> bool{
		let head = self.anchs[self.anchs.len()-1];
		if head.0 < 0. {true}
		else if head.1 < 0. {true}
		else if head.0 > self.game_size.0 {true}
		else if head.1 > self.game_size.1 {true}
		else if self.body_coll() {true}
		else {false}
	}

	pub fn try_eat(&self, f: &Food) -> bool{
		let head = self.anchs[self.anchs.len()-1];
		let sz = self.size + f.act_rad*2.;

		if (head.0 - f.pos.0).abs() + (head.1 - f.pos.1).abs() < sz {true}
		else {false}
	}


	pub fn key_press(&mut self, k: Key){
		if k == Key::Up{
			if self.dir != 2{
				self.nextdir = 0;
			}
		}else if k == Key::Right{
			if self.dir != 3{
				self.nextdir = 1;
			}
		}else if k == Key::Down{
			if self.dir != 0{
				self.nextdir = 2;
			}
		}else if k == Key::Left{
			if self.dir != 1{
				self.nextdir = 3;
			}
		}

		let cur_sp = self.speed;

		self.speed = match k{
			Key::Q => 1.,
			Key::W => 2.,
			Key::E => 3.,
			Key::R => 4.,
			Key::T => 5.,
			Key::Z => 6.,
			Key::U => 7.,
			Key::I => 8.,
			Key::O => 9.,
			Key::P => 10.,
			_ => cur_sp,
		}
	}
}

impl Object for Snake{
	fn update(&mut self, _: i64, _: f64){
		let mut sz = self.anchs.len();
		let dir = self.dir;

		let mut speed = self.speed;

		if self.nextdir != self.dir{
			let new_anch: (f64, f64) = self.anchs[sz-1];
			self.anchs.push( new_anch);
			sz = self.anchs.len();
			self.dir = self.nextdir;
		}

		let mv =
			if dir == 0{
				(0.,-1.)
			}else if dir == 1{
				(1.,0.)
			}else if dir == 2{
				(0.,1.)
			}else{
				(-1.,0.)
			};


		self.anchs[sz-1].0 += mv.0 * speed;
		self.anchs[sz-1].1 += mv.1 * speed;

		let mut diff = (self.anchs[1].0-self.anchs[0].0, self.anchs[1].1 - self.anchs[0].1);
		let mut dist = diff.0.abs() + diff.1.abs();

		if dist <= speed{
			self.anchs.remove(0);
			speed -= dist;
			diff = (self.anchs[1].0-self.anchs[0].0, self.anchs[1].1 - self.anchs[0].1);
			dist = diff.0.abs() + diff.1.abs();
		}

		let norm_mv = (
			if diff.0.abs() <= 1. {0.} else if diff.0 > 0. {1.} else {-1.},
			if diff.1.abs() <= 1. {0.} else if diff.1 > 0. {1.} else {-1.},
		);

		if self.grow > 1. {
			self.grow -= 1.;
			speed -= 0.5;
		}

		self.anchs[0].0 += norm_mv.0 * speed;
		self.anchs[0].1 += norm_mv.1 * speed;
	}

	fn render(&self, c: Context, g: &mut GlGraphics){
		let sz = self.anchs.len()-1;
		for i in 0..sz{
			let mut old = self.anchs[i];
			let mut new = self.anchs[i+1];
			if old.0 > new.0 {swap(&mut old.0, &mut new.0);}
			if old.1 > new.1 {swap(&mut old.1, &mut new.1);}
			let sz = self.size;
			rectangle(
				[0.,1.,0.,1.],
				[old.0 -sz , old.1 - sz, new.0 - old.0 + 2.*sz, new.1 - old.1 + 2.*sz],
				c.transform, g
			);
		}
	}
}
