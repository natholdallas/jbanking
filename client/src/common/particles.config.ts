export const options = {
  fpsLimit: 60,
  fullScreen: { enable: true },
  particles: {
    number: {
      value: 50
    },
    shape: {
      type: 'circle'
    },
    opacity: {
      value: 0.5
    },
    size: {
      value: 400,
      random: {
        enable: true,
        minimumValue: 200
      }
    },
    move: {
      enable: true,
      speed: 10,
      direction: 'top',
      outModes: {
        default: 'out',
        top: 'destroy',
        bottom: 'none'
      }
    }
  },
  interactivity: {
    detectsOn: 'canvas',
    events: {
      resize: true
    }
  },
  detectRetina: true,
  themes: [
    {
      name: 'light',
      default: {
        value: true,
        mode: 'light'
      },
      options: {
        background: {
          color: '#f7f8ef'
        },
        particles: {
          color: {
            value: ['#5bc0eb', '#fde74c', '#9bc53d', '#e55934', '#fa7921']
          }
        }
      }
    },
    {
      name: 'dark',
      default: {
        value: true,
        mode: 'dark'
      },
      options: {
        background: {
          color: '#080710'
        },
        particles: {
          color: {
            value: ['#004f74', '#5f5800', '#245100', '#7d0000', '#810c00']
          }
        }
      }
    }
  ],
  emitters: {
    direction: 'top',
    position: {
      x: 50,
      y: 150
    },
    rate: {
      delay: 0.2,
      quantity: 2
    },
    size: {
      width: 100,
      height: 0
    }
  }
}
