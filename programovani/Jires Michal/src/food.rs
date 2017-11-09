use piston_window::*;
use opengl_graphics::GlGraphics;

use piston_window::types::Color;

use object::Object;

pub struct Food{
	pub pos: (f64, f64),
	pub rad: f64,
	pub act_rad: f64,

	pub ch: bool,

	pub color: Color,
	pub score: i64,
	pub grow: f64,
	pub decay: i64,
}

impl Object for Food{
	fn update(&mut self, _: i64, _: f64){
		if self.ch{
			self.act_rad += 0.0625;
			if self.act_rad > self.rad {self.ch = false;}
		}else{
			self.act_rad -= 0.0625;
			if self.act_rad < self.rad / 2. {self.ch = true;}
		}
		self.decay -= 1;
	}
	fn render(&self, c: Context, g: &mut GlGraphics){
		circle_arc(
			self.color,
			self.act_rad, 0., 6.28,
			[self.pos.0, self.pos.1, self.act_rad, self.act_rad],
			c.transform, g
		);
	}
}
