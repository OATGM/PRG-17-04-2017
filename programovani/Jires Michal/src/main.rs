extern crate piston_window;
use piston_window::*;

extern crate glutin_window;
use glutin_window::GlutinWindow;

extern crate opengl_graphics;
use opengl_graphics::GlGraphics;

extern crate rand;
use rand::*;

mod object;
use object::*;

mod snake;
use snake::*;

mod food;
use food::*;


use std::ops::Deref;

//use std::f64::consts::PI;

struct App{
	cursor: (f64, f64),
	tick: i64,
	score: i64,
	game_over: bool,


	foods: Vec<Box<Food>>,
	snake: Snake,
}

impl App{
	fn key_press(&mut self, b: Key){
		self.snake.key_press(b);
	}

	fn move_cursor(&mut self, cursor: (f64, f64)){
		self.cursor = cursor;
		//self.snake.move_cursor(cursor);
	}

	fn update(&mut self, dt: f64){
		if self.snake.test_die(){
			if !self.game_over {println!("Game over! Your score: {}", self.score);}
			self.game_over = true;
			return;
		}

		if self.tick % 60 == 0{
			println!("Time: {} s", self.tick/60);
		}

		self.tick+=1;
		for food in &mut self.foods{
			food.update(self.tick, dt);
		}

		self.snake.update(self.tick, dt);

		for i in 0..self.foods.len(){
			if self.snake.try_eat(self.foods[i].deref()){
				self.snake.grow = self.foods[i].grow;
				self.score += self.foods[i].score * (self.snake.speed as i64);
				self.foods.remove(i);
				println!("Score: {}", self.score);
			}else if self.foods[i].decay < 0{
				self.foods.remove(i);
			}
		}


		if self.foods.len() == 0 {
			let rnd = random::<f64>();
			self.foods.push(
				if rnd > 0.25{
					Box::new(
						Food{
							pos: (8. + random::<f64>() * 944., 8. + random::<f64>() * 524.),
							rad: 4.,
							act_rad: 4.,
							color: [1.,0.8,0.,1.],
							ch: false,
							score: 1,
							grow: 16.,
							decay: 600000,
						}
					)
				}else if rnd > 0.0625{
					Box::new(
						Food{
							pos: (12. + random::<f64>() * 936., 12. + random::<f64>() * 516.),
							rad: 6.,
							act_rad: 6.,
							color: [1.,0.,0.,1.],
							ch: false,
							score: 8,
							grow: 40.,
							decay: 1800,
						}
					)
				}else{
					Box::new(
						Food{
							pos: (6. + random::<f64>() * 948., 6. + random::<f64>() * 528.),
							rad: 3.,
							act_rad: 3.,
							color: [0.,0.,1.,1.],
							ch: false,
							score: -12,
							grow: 80.,
							decay: 600,
						}
					)
				}
			);
		}
	}

	fn render(&mut self, c: Context, g: &mut GlGraphics){
		clear([0.0; 4], g);
		/*rectangle([1.0, 0.0, 0.0, 1.0], // red
			[0.0, 0.0, 100.0, 100.0],
			c.transform, g);
		circle_arc([1.0, 0.0, 0.0, 1.0],
			20.0, 0. + change, 2.* (PI - change),
			[300., 300., 40., 40.],
			c.transform, g);*/

		/*text([1.0, 0.0, 0.0, 1.0],
			16,
			"Game over!",
			_,
			c.transform, g);*/

		self.snake.render(c, g);
		for food in &self.foods{
			food.render(c, g);
		}

		if self.game_over{
			line([1.0, 0.0, 0.0, 1.0],
				2.,
				[0., 0., 960., 540.],
				c.transform, g);
			line([1.0, 0.0, 0.0, 1.0],
				2.,
				[0., 540., 960., 0.],
				c.transform, g);
		}
	}
}

fn main() {

	let mut app = App{
		cursor: (0., 0.),
		tick: 0,
		score: 0,
		game_over: false,
		foods: Vec::new(),
		snake: Snake::new(
			(100., 100.),
			(100., 324.),
			(960., 540.),
			8.,
			1.,
		),
	};


	let opengl = OpenGL::V2_1;

	let mut window: GlutinWindow = WindowSettings::new("[FLOAT] - window", [960, 540])
				.exit_on_esc(true).opengl(opengl).build().unwrap();

	let mut glgr = GlGraphics::new(opengl);

	let mut events = Events::new(EventSettings::new());

	events.set_ups(60);
	events.set_max_fps(60);

	while let Some(e) = events.next(&mut window){
		if let Some(r) = e.render_args(){
			glgr.draw(r.viewport(), |c, g| {
				app.render(c, g);
			});
		}


		if let Some(u) = e.update_args(){
			app.update(u.dt);
		}


		if let Some(p) = e.press_args(){
			match p{
				Button::Keyboard(b) => {
					app.key_press(b);
				}
				Button::Mouse(b) => {
					//println!("m {:?}", b);
				}
				Button::Controller(b) => {
					//println!("c {:?}", b);
				}
			}
		}


		/*if let Some(p) = e.release_args(){
			match p{
				Button::Keyboard(b) => {
					//println!("rk {:?}", b);
				}
				Button::Mouse(b) => {
					//println!("rm {:?}", b);
				}
				Button::Controller(b) => {
					//println!("rc {:?}", b);
				}
			}
		}*/


		e.mouse_cursor(|x, y| {
			app.move_cursor((x, y));
		});
		//e.mouse_scroll(|dx, dy| println!("Scrolled mouse '{}, {}'", dx, dy));
		//e.mouse_relative(|dx, dy| println!("Relative mouse moved '{} {}'", dx, dy));
		//e.text(|text| println!("Typed '{}'", text));
		//e.resize(|w, h| println!("Resized '{}, {}'", w, h));
		/*if let Some(cursor) = e.cursor_args() {
			//if cursor { println!("Mouse entered"); }
			//else { println!("Mouse left"); }
		};*/


		/*if let Some(arg) = e.idle_args(){
			//println!("Idle {}", arg.dt);
		}*/
	}
}
