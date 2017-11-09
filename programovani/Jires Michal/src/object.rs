use piston_window::*;
use opengl_graphics::GlGraphics;

pub trait Object{
///ticks, dt
	fn update(&mut self, _: i64, _: f64){}
	fn render(&self, _: Context, _: &mut GlGraphics){}
}
